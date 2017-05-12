package ro.basilescu.bogdan.mvpapplication.data.remote;

/**
 * Created by bogdan.basilescu on 5/2/2017.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class MovieResponse model mapped for Json Schema for REST service
 */
public class ApiResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<ApiMovieResponse> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ApiMovieResponse> getResults() {
        return results;
    }

    public void setResults(List<ApiMovieResponse> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
