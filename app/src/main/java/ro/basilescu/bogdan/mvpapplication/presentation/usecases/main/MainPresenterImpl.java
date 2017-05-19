package ro.basilescu.bogdan.mvpapplication.presentation.usecases.main;

import java.util.ArrayList;
import java.util.List;

import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;
import ro.basilescu.bogdan.mvpapplication.presentation.models.MovieModel;
import ro.basilescu.bogdan.mvpapplication.presentation.models.MovieModelImpl;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.model.RequestCallback;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.presenter.AbsPresenter;

public class MainPresenterImpl extends AbsPresenter<MainContract.MainView> implements MainContract.MainPresenter<MainContract.MainView>, RequestCallback {

    private MovieModel movieModel;
    private List<Movie> movieList;

    public MainPresenterImpl(MainFragment view) {
        super(view);
        movieModel = MovieModelImpl.getInstance();
        movieList = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        //Empty
    }

    @Override
    public void onCreated() {
        if (movieModel.hasMovies()) {
            movieList.clear();
            movieList.addAll(movieModel.getMovies());
            getView().displayResults();
        } else {
            getView().displayNoResults();
            movieModel.fetchMovies(this);
        }
    }

    @Override
    public void onResume() {
        if (movieModel.hasMovies()) {
            movieList.clear();
            movieList.addAll(movieModel.getMovies());
            getView().displayResults();
        }
    }

    @Override
    public void onPause() {
        //Empty
    }

    @Override
    public void onDestroy() {
        //Empty
    }

    @Override
    public List<Movie> getItems() {
        return movieList;
    }

    @Override
    public Movie getItemAtPosition(int position) {
        return movieList.get(position);
    }

    @Override
    public int getMoviesCount() {
        return movieList.size();
    }

    @Override
    public void onSearchRequested() {
        String query = getView().getMovieQuery();
        movieModel.fetchSearchedMovies(query, this);
    }

    @Override
    public void onListItemClicked(int itemId) {
        getView().onListItemClicked(itemId);
    }

    @Override
    public void onAddMovieClicked() {
        getView().onAddMovieClicked();
    }

    @Override
    public void onReceived() {
        movieList.clear();
        movieList.addAll(movieModel.getMovies());
        getView().displayResults();
    }

    @Override
    public void onFailed() {
        getView().displayNoResults();
    }
}
