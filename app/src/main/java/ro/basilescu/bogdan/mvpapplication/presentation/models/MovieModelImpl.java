package ro.basilescu.bogdan.mvpapplication.presentation.models;

import java.util.ArrayList;
import java.util.List;

import ro.basilescu.bogdan.mvpapplication.data.ResponseUtils;
import ro.basilescu.bogdan.mvpapplication.data.remote.ApiResponse;
import ro.basilescu.bogdan.mvpapplication.data.remote.MovieApiClient;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.model.RequestCallback;
import rx.Subscriber;

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
    public void fetchMovies(final RequestCallback requestCallback) {
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

    @Override
    public void fetchSearchedMovies(String query, final RequestCallback requestCallback) {
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
}
