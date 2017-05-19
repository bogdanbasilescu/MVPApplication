package ro.basilescu.bogdan.mvpapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;

public class MVPApplication extends Application {

    private static MVPApplication mvpApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mvpApplication = this;
        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                .withMetaTables()
                                .build())
                        .build());

    }

    public static MVPApplication getInstance() {
        return mvpApplication;
    }
}
