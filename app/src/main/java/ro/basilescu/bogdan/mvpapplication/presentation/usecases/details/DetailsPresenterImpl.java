package ro.basilescu.bogdan.mvpapplication.presentation.usecases.details;

import ro.basilescu.bogdan.mvpapplication.presentation.models.MovieModel;
import ro.basilescu.bogdan.mvpapplication.presentation.models.MovieModelImpl;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.presenter.AbsPresenter;

/**
 * Created by bogdan.basilescu on 5/4/2017.
 */

public class DetailsPresenterImpl extends AbsPresenter<DetailsContract.DetailsView> implements DetailsContract.DetailsPresenter<DetailsContract.DetailsView> {

    private MovieModel movieModel;

    public DetailsPresenterImpl(DetailsFragment view) {
        super(view);
        movieModel = MovieModelImpl.getInstance();
    }

    @Override
    public void onCreate() {
        // Empty
    }

    @Override
    public void onCreated() {
        getView().displayMovie(movieModel.getMovie(getView().getCurrentId()));
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
    public void onUpNavigationClicked() {
        getView().onUpNavigationClicked();
    }
}
