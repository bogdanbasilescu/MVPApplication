package ro.basilescu.bogdan.mvpapplication.data.local.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import ro.basilescu.bogdan.mvpapplication.data.local.realm.tables.RealmMovie;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RealmDb implements MovieDataSource {

    private static final String REALM_NAME = "mvprealm.realm";
    private static final long SCHEMA_VERSION = 1;

    private static RealmDb realmDb;

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

    @Override
    public void addOrUpdateMovie(String title, String originalTitle, String overview, String releaseDate, Action1<RealmObject> action) {
        Realm realm = getRealmInstance();
        RealmMovie realmMovie = new RealmMovie();
        realmMovie.setId(getIdAutoIncrement());
        realmMovie.setTitle(title);
        realmMovie.setOriginalTitle(originalTitle);
        realmMovie.setOverview(overview);
        realmMovie.setReleaseDate(releaseDate);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmMovie).asObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(action);
        realm.commitTransaction();
        realm.close();
    }

    private int getIdAutoIncrement() {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        Number number = realm.where(RealmMovie.class).findAll().max("id");
        realm.commitTransaction();
        realm.close();
        if (number == null) {
            return 1;
        } else {
            return number.intValue() + 1;
        }
    }

    @Override
    public RealmMovie getMovie(int id, Action1<RealmObject> subscriber) {
        Realm realm = getRealmInstance();
        return null;
    }

    @Override
    public RealmList<RealmMovie> getMovies(Action1<RealmList<RealmObject>> subscriber) {
        Realm realm = getRealmInstance();
        return null;
    }

    @Override
    public void deleteMovie(int id, Action1<RealmObject> subscriber) {
        Realm realm = getRealmInstance();
    }
}
