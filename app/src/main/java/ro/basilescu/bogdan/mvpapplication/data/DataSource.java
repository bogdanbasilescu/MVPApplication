package ro.basilescu.bogdan.mvpapplication.data;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import ro.basilescu.bogdan.mvpapplication.data.local.realm.tables.RealmMovie;
import rx.functions.Action1;

public interface DataSource<T> {
    void add(T item);
    List<T> getAll();
    T get(T item);
    void update(T item);
    void delete(T item);
}

