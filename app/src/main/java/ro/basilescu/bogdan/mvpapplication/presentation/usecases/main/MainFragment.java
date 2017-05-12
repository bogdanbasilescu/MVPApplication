package ro.basilescu.bogdan.mvpapplication.presentation.usecases.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import ro.basilescu.bogdan.mvpapplication.R;
import ro.basilescu.bogdan.mvpapplication.presentation.models.Movie;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.view.AbsBaseFragmentView;
import ro.basilescu.bogdan.mvpapplication.presentation.usecases.addmovie.AddMovieFragment;
import ro.basilescu.bogdan.mvpapplication.presentation.usecases.details.DetailsFragment;
import ro.basilescu.bogdan.mvpapplication.presentation.utils.ActivityUtils;
import ro.basilescu.bogdan.mvpapplication.presentation.utils.NavigationUtils;

public class MainFragment extends AbsBaseFragmentView<MainContract.MainPresenter> implements MainContract.MainView<MainContract.MainPresenter> {
    @BindView(R.id.ll_positive_state)
    View viewPositive;
    @BindView(R.id.ll_neg_state)
    View viewNegative;
    @BindView(R.id.et_query)
    EditText etSearch;
    @BindView(R.id.rv_main_movies)
    RecyclerView recyclerView;
    @BindString(R.string.details_fragment_tag)
    String detailsFragmentTag;
    @BindString(R.string.add_movie_fragment_tag)
    String addMovieFragmentTag;
    //Movie RecyclerAdapter
    private MovieRecyclerAdapter movieRecyclerAdapter;

    @Override
    public void showMessage(int message) {
        if (isAdded()) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ActivityUtils.setDisplayUpNavigation((AppCompatActivity) getActivity(), false);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getPresenter().onSearchRequested();
                    hideKeyboard(getView(), getContext());
                    return true;
                }
                return false;
            }
        });
        movieRecyclerAdapter = new MovieRecyclerAdapter(getContext(), getPresenter().getItems());
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                getPresenter().onAddMovieClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public MainContract.MainPresenter makePresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public void displayResults() {
        movieRecyclerAdapter.notifyDataSetChanged();
        shouldShowList(true);
    }

    @Override
    public void displayNoResults() {
        shouldShowList(false);
    }

    @Override
    public String getMovieQuery() {
        return etSearch.getText().toString();
    }

    @Override
    public void onListItemClicked(int itemId) {
        NavigationUtils.replaceFragmentWith(getActivity().getSupportFragmentManager(),
                NavigationUtils.getInstanceWithArgument(new DetailsFragment(), itemId),
                detailsFragmentTag,
                R.id.fragment_container);
    }

    @Override
    public void onAddMovieClicked() {
        NavigationUtils.replaceFragmentWith(getActivity().getSupportFragmentManager(),
                new AddMovieFragment(),
                addMovieFragmentTag,
                R.id.fragment_container);
    }

    private void shouldShowList(boolean show) {
        viewNegative.setVisibility(show ? View.GONE : View.VISIBLE);
        viewPositive.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void hideKeyboard(View view, Context context) {
        if (view != null) {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieHolder> {

        private Context context;

        public MovieRecyclerAdapter(Context context, List<Movie> movieList) {
            this.context = context;
        }

        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_movie, null, false);
            return new MovieHolder(view);
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            holder.bind(getPresenter().getItemAtPosition(position));
        }

        @Override
        public int getItemCount() {
            return getPresenter().getMoviesCount();
        }

        public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView textViewTitle;
            public TextView textViewDescrition;
            public TextView textViewActorList;
            public RatingBar ratingBar;
            private Movie movie;

            public MovieHolder(View view) {
                super(view);
                view.setOnClickListener(this);
                textViewTitle = (TextView) view.findViewById(R.id.tv_movie_title);
                textViewDescrition = (TextView) view.findViewById(R.id.tv_movie_description);
                textViewActorList = (TextView) view.findViewById(R.id.tv_movie_actor_list);
                ratingBar = (RatingBar) view.findViewById(R.id.rb_movie);
            }

            public void bind(Movie movie) {
                this.movie = movie;
                textViewTitle.setText(movie.getTitle());
                textViewDescrition.setText(movie.getOriginalTitle());
                textViewActorList.setText(movie.getOverview());
                ratingBar.setRating((float) movie.getVoteAverage());
            }

            @Override
            public void onClick(View view) {
                getPresenter().onListItemClicked(movie.getId());
            }
        }
    }
}
