package ro.basilescu.bogdan.mvpapplication.presentation.usecases.main;

import java.util.List;

import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.Presenter;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.View;

/**
 * Created by bogdan.basilescu on 5/8/2017.
 */

public interface MainContract {

    interface MainPresenter<P extends View> extends Presenter<P> {
        List<Movie> getItems();

        Movie getItemAtPosition(int position);

        int getMoviesCount();

        void onSearchRequested();

        void onListItemClicked(int itemId);

        void onAddMovieClicked();
    }

    interface MainView<P extends Presenter> extends View<P> {
        void displayResults();

        void displayNoResults();

        String getMovieQuery();

        void onListItemClicked(int itemId);

        void onAddMovieClicked();
    }
}
