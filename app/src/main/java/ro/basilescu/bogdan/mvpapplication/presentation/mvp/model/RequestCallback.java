package ro.basilescu.bogdan.mvpapplication.presentation.mvp.model;

public interface RequestCallback {
    void onReceived();

    void onFailed();
}
