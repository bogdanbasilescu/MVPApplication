package ro.basilescu.bogdan.mvpapplication.data.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.basilescu.bogdan.mvpapplication.BuildConfig;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieApiClient {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static MovieApiClient movieApiClient = null;
    private MovieApiService movieApiService;

    private MovieApiClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        movieApiService = retrofit.create(MovieApiService.class);
    }

    public static MovieApiClient getInstance() {
        if (movieApiClient == null) movieApiClient = new MovieApiClient();
        return movieApiClient;
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
