package ro.basilescu.bogdan.mvpapplication.presentation.mvp;

/**
 * Created by bogdan.basilescu on 4/26/2017.
 */

public interface Presenter<V extends View> {

    V getView();

    // Lifecycle
    void onCreate();

    void onCreated();

    void onResume();

    void onPause();

    void onDestroy();
}
