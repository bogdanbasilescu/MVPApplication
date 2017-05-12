package ro.basilescu.bogdan.mvpapplication;

import android.app.Application;

import io.realm.Realm;

public class MVPApplication extends Application {

    private static MVPApplication mvpApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mvpApplication = this;
        Realm.init(this);
    }

    public static MVPApplication getInstance() {
        return mvpApplication;
    }
}
