package ro.basilescu.bogdan.mvpapplication.data.local.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDb {

    private static final String REALM_NAME = "mvprealm.realm";
    private static final long SCHEMA_VERSION = 1;

    private static RealmDb realmDb;
    private Realm realm;

    private RealmDb() {
        //Empty
    }

    public static RealmDb getInstance() {
        if (realmDb == null) realmDb = new RealmDb();
        return realmDb;
    }

    public Realm getRealmInstance() {
        return Realm.getInstance(new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .build());
    }

}
