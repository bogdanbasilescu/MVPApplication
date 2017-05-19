package ro.basilescu.bogdan.mvpapplication.presentation.usecases.addmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindString;
import butterknife.BindView;
import ro.basilescu.bogdan.mvpapplication.R;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.view.AbsBaseFragmentView;
import ro.basilescu.bogdan.mvpapplication.presentation.utils.NavigationUtils;

public class AddMovieFragment extends AbsBaseFragmentView<AddMovieContract.AddMoviePresenter> implements AddMovieContract.AddMovieView<AddMovieContract.AddMoviePresenter> {

    @BindView(R.id.et_movie_title)
    EditText etMovieTitle;
    @BindView(R.id.et_movie_originalTitle)
    EditText etMovieOriginalTitle;
    @BindView(R.id.et_movie_overview)
    EditText etMovieOverview;
    @BindView(R.id.et_movie_release_date)
    EditText etMovieReleaseDate;
    @BindView(R.id.btn_add_movie)
    Button btnAddMovie;
    @BindString(R.string.add_movie_fragment_tag)
    String addMovieFragmentTag;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onAddMovieClicked();
            }
        });
    }

    @Override
    public void showMessage(int message) {
        if (isAdded()) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_movie;
    }

    @Override
    public AddMovieContract.AddMoviePresenter makePresenter() {
        return new AddMoviePresenterImpl(this);
    }

    @Override
    public String getTitle() {
        return etMovieTitle.getText().toString();
    }

    @Override
    public String getOriginalTitle() {
        return etMovieOriginalTitle.getText().toString();
    }

    @Override
    public String getOverview() {
        return etMovieOverview.getText().toString();
    }

    @Override
    public String getReleaseDate() {
        return etMovieReleaseDate.getText().toString();
    }

    @Override
    public void onAddedMovieEvent() {
        FragmentActivity activity = getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        NavigationUtils.popFromStackFragment(fragmentManager, addMovieFragmentTag);
    }
}
