package ro.basilescu.bogdan.mvpapplication.presentation.mvp;

/**
 * Created by bogdan.basilescu on 4/26/2017.
 */

public interface View<P extends Presenter> {

    P getPresenter();

    void showMessage(int message);

}
