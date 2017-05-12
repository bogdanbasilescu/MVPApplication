package ro.basilescu.bogdan.mvpapplication.presentation.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.Presenter;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.View;

public abstract class AbsBaseFragmentView<P extends Presenter> extends Fragment implements View<P> {

    // Butterknife binderInterface
    private Unbinder unbinder;

    private P presenter;

    public AbsBaseFragmentView() {
        // Empty
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(getLayoutId(), null, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = makePresenter();
        presenter.onCreate();
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public abstract int getLayoutId();

    public abstract P makePresenter();

}

