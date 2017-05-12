package ro.basilescu.bogdan.mvpapplication.presentation.usecases.addmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import ro.basilescu.bogdan.mvpapplication.R;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.view.AbsBaseFragmentView;

public class AddMovieFragment extends AbsBaseFragmentView<AddMovieContract.AddMoviePresenter> implements AddMovieContract.AddMovieView<AddMovieContract.AddMoviePresenter> {

    @BindView(R.id.et_movie_title)
    EditText etMovieTitle;
    @BindView(R.id.et_movie_overview)
    EditText etMovieOverview;
    @BindView(R.id.et_movie_originalLanguage)
    EditText etMovieOriginalLanguage;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}
