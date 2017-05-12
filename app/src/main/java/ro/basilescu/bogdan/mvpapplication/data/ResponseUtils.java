package ro.basilescu.bogdan.mvpapplication.data;

import java.util.ArrayList;
import java.util.List;

import ro.basilescu.bogdan.mvpapplication.data.remote.ApiMovieResponse;
import ro.basilescu.bogdan.mvpapplication.data.remote.ApiResponse;
import ro.basilescu.bogdan.mvpapplication.presentation.models.AppResponse;
import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;

public class ResponseUtils {

    /*
    Image baseUrls
     */
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";

    public static AppResponse makeFromApiResponseToAppResponse(ApiResponse apiResponse) {
        AppResponse appResponse = new AppResponse();
        appResponse.setPage(apiResponse.getPage());
        appResponse.setTotalPages(apiResponse.getTotalPages());
        appResponse.setResults(makeFromApiMovieResponsesToMovies(apiResponse.getResults()));
        appResponse.setTotalResults(apiResponse.getTotalResults());
        return appResponse;
    }

    private static List<Movie> makeFromApiMovieResponsesToMovies(List<ApiMovieResponse> apiMovieResponses) {
        List<Movie> movieList = new ArrayList<>();
        for (ApiMovieResponse apiMovieResponse : apiMovieResponses) {
            movieList.add(makeFromApiMovieResponseToMovie(apiMovieResponse));
        }
        return movieList;
    }

    public static Movie makeFromApiMovieResponseToMovie(ApiMovieResponse apiMovieResponse) {
        Movie movie = new Movie();
        movie.setId(apiMovieResponse.getId());
        movie.setAdult(apiMovieResponse.isAdult());
        movie.setBackdropPath(apiMovieResponse.getBackdropPath());
        movie.setGenreIds(apiMovieResponse.getGenreIds());
        movie.setOriginalLanguage(apiMovieResponse.getOriginalLanguage());
        movie.setOriginalTitle(apiMovieResponse.getOriginalTitle());
        movie.setTitle(apiMovieResponse.getTitle());
        movie.setOverview(apiMovieResponse.getOverview());
        movie.setPopularity(apiMovieResponse.getPopularity());
        movie.setPosterPath(IMAGE_BASE_URL + apiMovieResponse.getPosterPath());
        movie.setReleaseDate(apiMovieResponse.getReleaseDate());
        movie.setVideo(apiMovieResponse.isVideo());
        movie.setVoteAverage(apiMovieResponse.getVoteAverage());
        movie.setVoteCount(apiMovieResponse.getVoteCount());
        return movie;
    }
}
