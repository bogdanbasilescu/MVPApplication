package ro.basilescu.bogdan.mvpapplication.data.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.basilescu.bogdan.mvpapplication.BuildConfig;
import ro.basilescu.bogdan.mvpapplication.MVPApplication;
import ro.basilescu.bogdan.mvpapplication.presentation.utils.ConnectionUtils;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieApiClient {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static MovieApiClient movieApiClient = null;
    private MovieApiService movieApiService;

    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    private MovieApiClient() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(getOkHttpClient());
        Retrofit retrofit = builder.build();
        movieApiService = retrofit.create(MovieApiService.class);
    }

    public static MovieApiClient getInstance() {
        if (movieApiClient == null) movieApiClient = new MovieApiClient();
        return movieApiClient;
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .addInterceptor(getOfflineCacheInterceptor())
                .addNetworkInterceptor(getCacheInterceptor())
                .cache(getCacheFile())
                .build();
    }

    private Cache getCacheFile() {
        return new Cache(new File(MVPApplication.getInstance().getCacheDir(), "http-cache"), CACHE_SIZE);
    }

    private Gson getGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    private Interceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .onlyIfCached()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build();
            }
        };
    }

    private Interceptor getOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!ConnectionUtils.isNetworkAvailable()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    public Subscription getTopRatedMovies(Subscriber<ApiResponse> subscriber) {
        return movieApiService.getTopRatedMovies(BuildConfig.MOVIE_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription getSearchedMovie(Subscriber<ApiResponse> subscriber, String query) {
        return movieApiService.getSearchedMovie(BuildConfig.MOVIE_API_KEY, query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription getMovieDetails(int id, Subscriber<ApiMovieResponse> subscriber) {
        return movieApiService.getMovieDetails(BuildConfig.MOVIE_API_KEY, id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
