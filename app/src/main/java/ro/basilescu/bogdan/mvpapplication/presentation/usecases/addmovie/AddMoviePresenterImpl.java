package ro.basilescu.bogdan.mvpapplication.presentation.usecases.addmovie;

import ro.basilescu.bogdan.mvpapplication.presentation.mvp.presenter.AbsPresenter;

/**
 * Created by bogdan.basilescu on 5/9/2017.
 */

public class AddMoviePresenterImpl extends AbsPresenter<AddMovieContract.AddMovieView> implements AddMovieContract.AddMoviePresenter<AddMovieContract.AddMovieView> {

    public AddMoviePresenterImpl(AddMovieFragment view) {
        super(view);
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
}
