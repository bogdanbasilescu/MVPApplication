package ro.basilescu.bogdan.mvpapplication.presentation.usecases.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindString;
import butterknife.BindView;
import ro.basilescu.bogdan.mvpapplication.R;
import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.view.AbsBaseFragmentView;
import ro.basilescu.bogdan.mvpapplication.presentation.utils.ActivityUtils;
import ro.basilescu.bogdan.mvpapplication.presentation.utils.NavigationUtils;

public class DetailsFragment extends AbsBaseFragmentView<DetailsContract.DetailsPresenter> implements DetailsContract.DetailsView<DetailsContract.DetailsPresenter> {
    @BindView(R.id.iv_movie_poster)
    ImageView ivMoviePoster;
    @BindView(R.id.tv_movie_original_title)
    TextView tvOriginalTitle;
    @BindView(R.id.tv_movie_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_movie_overview)
    TextView tvMovieOverview;
    @BindView(R.id.tv_movie_original_language)
    TextView tvMovieOriginalLanguage;
    @BindView(R.id.tv_movie_vote_average)
    TextView tvMovieVoteAverage;
    @BindString(R.string.details_fragment_tag)
    String detailsFragmentTag;
    // Movie id
    private int id;

    @Override
    public void showMessage(int message) {
        if (isAdded()) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(NavigationUtils.ARGS_ID);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ActivityUtils.setDisplayUpNavigation((AppCompatActivity) getActivity(), true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getPresenter().onUpNavigationClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    public DetailsContract.DetailsPresenter makePresenter() {
        return new DetailsPresenterImpl(this);
    }

    @Override
    public int getCurrentId() {
        return id;
    }

    @Override
    public void onUpNavigationClicked() {
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        NavigationUtils.popFromStackFragment(fragmentManager, detailsFragmentTag);
    }

    @Override
    public void displayMovie(Movie movie) {
        if (movie == null) {
            showMessage(R.string.message_error_get_movie);
        } else {
            Glide.with(this)
                    .load(movie.getPosterPath())
                    .into(ivMoviePoster);
            tvOriginalTitle.setText(movie.getOriginalTitle());
            tvMovieOverview.setText(movie.getOverview());
            tvReleaseDate.setText(movie.getReleaseDate());
            tvMovieOriginalLanguage.setText(movie.getOriginalLanguage());
            tvMovieVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        }
    }
}
