package ro.basilescu.bogdan.mvpapplication.data.local.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import ro.basilescu.bogdan.mvpapplication.data.local.realm.tables.RealmMovie;
import rx.functions.Action1;

public interface MovieDataSource {
    void addOrUpdateMovie(String title, String originalTitle, String overview, String releaseDate, Action1<RealmObject> subscriber);

    RealmMovie getMovie(int id, Action1<RealmObject> subscriber);

    RealmList<RealmMovie> getMovies(Action1<RealmList<RealmObject>> subscriber);

    void deleteMovie(int id, Action1<RealmObject> subscriber);
}
