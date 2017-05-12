package ro.basilescu.bogdan.mvpapplication.presentation.models;

import java.util.List;

import ro.basilescu.bogdan.mvpapplication.presentation.mvp.Model;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.model.RequestCallback;

/**
 * Created by bogdan.basilescu on 4/26/2017.
 */

public interface MovieModel extends Model {
    List<Movie> getMovies();

    void fetchMovies(RequestCallback requestCallback);

    void fetchSearchedMovies(String query, RequestCallback requestCallback);

    boolean hasMovies();

    Movie getMovie(int id);

    void clearMovies();

    void sortMovies();
}
