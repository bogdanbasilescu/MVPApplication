package ro.basilescu.bogdan.mvpapplication.presentation.mvp.presenter;

import ro.basilescu.bogdan.mvpapplication.presentation.mvp.Presenter;
import ro.basilescu.bogdan.mvpapplication.presentation.mvp.View;

/**
 * Created by bogdan.basilescu on 4/26/2017.
 */

public abstract class AbsPresenter<V extends View> implements Presenter<V> {
    private V view;

    public AbsPresenter(V view) {
        this.view = view;
    }

    @Override
    public V getView() {
        return view;
    }

    public abstract void onCreate();

    public abstract void onCreated();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onDestroy();
}
