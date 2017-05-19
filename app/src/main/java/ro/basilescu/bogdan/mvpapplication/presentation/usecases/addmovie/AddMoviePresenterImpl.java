package ro.basilescu.bogdan.mvpapplication.presentation.usecases.addmovie;

import java.util.ArrayList;
import java.util.List;

import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;
import ro.basilescu.bogdan.mvpapplication.presentation.models.MovieModel;
import ro.basilescu.bogdan.mvpapplication.presentation.models.MovieModelImpl;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.model.RequestCallback;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.presenter.AbsPresenter;

public class AddMoviePresenterImpl extends AbsPresenter<AddMovieContract.AddMovieView> implements AddMovieContract.AddMoviePresenter<AddMovieContract.AddMovieView>, RequestCallback {

    private MovieModel movieModel;
    private List<Movie> movieList;

    public AddMoviePresenterImpl(AddMovieFragment view) {
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
        //Empty
    }

    @Override
    public void onResume() {
        //Empty
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
    public void onAddMovieClicked() {
        String title = getView().getTitle();
        String originalTitle = getView().getOriginalTitle();
        String overview = getView().getOverview();
        String releaseDate = getView().getReleaseDate();
        movieModel.addMovie(title, originalTitle, overview, releaseDate, this);
    }

    @Override
    public void onReceived() {
        getView().onAddedMovieEvent();
    }

    @Override
    public void onFailed() {

    }
}
