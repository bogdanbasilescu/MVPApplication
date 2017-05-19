package ro.basilescu.bogdan.mvpapplication.presentation.usecases.addmovie;

import ro.basilescu.bogdan.mvpapplication.presentation.mvp.Presenter;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.View;

public interface AddMovieContract {

    interface AddMoviePresenter<V extends View> extends Presenter<V> {
        void onAddMovieClicked();
    }

    interface AddMovieView<P extends Presenter> extends View<P> {
        String getTitle();

        String getOriginalTitle();

        String getOverview();

        String getReleaseDate();

        void onAddedMovieEvent();
    }
}
