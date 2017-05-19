package ro.basilescu.bogdan.mvpapplication.presentation.models;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import ro.basilescu.bogdan.mvpapplication.BuildConfig;
import ro.basilescu.bogdan.mvpapplication.data.ResponseUtils;
import ro.basilescu.bogdan.mvpapplication.data.local.realm.RealmDb;
import ro.basilescu.bogdan.mvpapplication.data.local.realm.tables.RealmMovie;
import ro.basilescu.bogdan.mvpapplication.data.remote.ApiResponse;
import ro.basilescu.bogdan.mvpapplication.data.remote.MovieApiClient;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.model.RequestCallback;
import rx.Subscriber;
import rx.functions.Action1;

public class MovieModelImpl implements MovieModel {

    private static MovieModelImpl movieModel;
    private List<Movie> movieList;

    private MovieModelImpl() {
        movieList = new ArrayList<>();
    }

    public static MovieModelImpl getInstance() {
        if (movieModel == null) movieModel = new MovieModelImpl();
        return movieModel;
    }

    @Override
    public void fetchMovies(RequestCallback requestCallback) {
        if (BuildConfig.DEBUG) {
            switch (BuildConfig.FLAVOR) {
                case BuildConfig.LOCAL:
                    loadLocalData(requestCallback);
                    break;
                case BuildConfig.REMOTE:
                    fetchRemoteData(requestCallback);
                    break;
                case BuildConfig.MOCK:
                    loadMockData(requestCallback);
                    break;
            }
        }
    }

    @Override
    public void fetchSearchedMovies(String query, final RequestCallback requestCallback) {
        if (BuildConfig.DEBUG) {
            switch (BuildConfig.FLAVOR) {
                case BuildConfig.LOCAL:
                    loadLocalSearchData(requestCallback, query);
                    break;
                case BuildConfig.REMOTE:
                    fetchRemoteSearchData(requestCallback, query);
                    break;
                case BuildConfig.MOCK:
                    loadMockSearchData(requestCallback, query);
                    break;
            }
        }
    }

    @Override
    public boolean hasMovies() {
        return !movieList.isEmpty();
    }

    @Override
    public List<Movie> getMovies() {
        return movieList;
    }

    @Override
    public void clearMovies() {

    }

    @Override
    public void sortMovies() {

    }

    @Override
    public Movie getMovie(int id) {
        for (Movie movie : movieList) {
            if (id == movie.getId()) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void addMovie(String title, String originalTitle, String overview, String releaseDate, final RequestCallback requestCallback) {
        RealmDb.getInstance().addOrUpdateMovie(title, originalTitle, overview, releaseDate, new Action1<RealmObject>() {
            @Override
            public void call(RealmObject realmObject) {
                Movie movie = ResponseUtils.makeFromRealmMovieToMovie((RealmMovie) realmObject);
                movieList.add(movie);
                requestCallback.onReceived();
            }
        });
    }

    private void fetchRemoteData(final RequestCallback requestCallback) {
        MovieApiClient.getInstance().getTopRatedMovies(new Subscriber<ApiResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                requestCallback.onFailed();
            }

            @Override
            public void onNext(ApiResponse apiResponse) {
                AppResponse appResponse = ResponseUtils.makeFromApiResponseToAppResponse(apiResponse);
                movieList.clear();
                movieList.addAll(appResponse.getResults());
                requestCallback.onReceived();
            }
        });
    }

    private void loadLocalData(final RequestCallback requestCallback) {
        // TODO: load local data
    }

    private void loadMockData(final RequestCallback requestCallback) {
        // TODO: load mock data
    }

    private void fetchRemoteSearchData(final RequestCallback requestCallback, String query) {
        MovieApiClient.getInstance().getSearchedMovie(new Subscriber<ApiResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                requestCallback.onFailed();
            }

            @Override
            public void onNext(ApiResponse apiResponse) {
                AppResponse appResponse = ResponseUtils.makeFromApiResponseToAppResponse(apiResponse);
                movieList.clear();
                movieList.addAll(appResponse.getResults());
                requestCallback.onReceived();
            }
        }, query);
    }

    private void loadLocalSearchData(final RequestCallback requestCallback, String query) {
        // TODO: add local search
    }

    private void loadMockSearchData(final RequestCallback requestCallback, String query) {
        // TODO: add mock search
    }
}
