package ro.basilescu.bogdan.mvpapplication.data.mock;

import java.util.ArrayList;
import java.util.List;

import ro.basilescu.bogdan.mvpapplication.data.MovieDataSource;
import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;
import rx.Subscriber;

public class MockDataSource implements MovieDataSource {

    private static MockDataSource mockDataSource;
    private List<Movie> movieList;

    private MockDataSource() {
        movieList = new ArrayList<>();
    }

    public MockDataSource getInstance() {
        if (mockDataSource == null ) {
            return new MockDataSource();
        }
        return mockDataSource;
    }

    @Override
    public void add(Movie item) {
        movieList.add(item);
    }

    @Override
    public List<Movie> getAll() {
        return null;
    }

    @Override
    public Movie get(Movie item) {
        return movieList.get(item.getId());
    }

    @Override
    public void update(Movie item) {
        for (Movie movie : movieList) {
            if (movie.getId() == item.getId()) {
                updateMovie(item);
            }
        }
    }

    @Override
    public void delete(Movie item) {
        for (Movie movie : movieList) {
            if (movie.getId() == item.getId()) {
                movieList.remove(item);
            }
        }
    }

    private List<Movie> getMockMovieList() {
        // TODO : set mock Movie list
        return null;
    }

    private void updateMovie(Movie movie) {
        // TODO: update Movie if necessary
    }
}
