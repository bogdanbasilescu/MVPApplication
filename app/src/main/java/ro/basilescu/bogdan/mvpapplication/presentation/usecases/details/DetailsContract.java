package ro.basilescu.bogdan.mvpapplication.presentation.usecases.details;

import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.Presenter;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.View;

public interface DetailsContract {

    interface DetailsPresenter<V extends View> extends Presenter<V> {
        void onUpNavigationClicked();
    }

    interface DetailsView<P extends Presenter> extends View<P> {
        void displayMovie(Movie movie);

        int getCurrentId();

        void onUpNavigationClicked();
    }
}
