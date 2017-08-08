package ro.basilescu.bogdan.mvpapplication.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ro.basilescu.bogdan.mvpapplication.data.remote.model.ApiMovieResponse;
import ro.basilescu.bogdan.mvpapplication.data.remote.model.ApiResponse;
import rx.Observable;

/**
 * Interface for defining endpoints for Movie fetching
 */
public interface MovieApiService {
    @GET("movie/top_rated")
    Observable<ApiResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<ApiMovieResponse> getMovieDetails(@Query("api_key") String apiKey, @Path("id") int id);

    @GET("search/movie")
    Observable<ApiResponse> getSearchedMovie(@Query("api_key") String apiKey, @Query("query") String query);
}
