package ro.basilescu.bogdan.mvpapplication.presentation.utils;

import android.support.v7.app.AppCompatActivity;

public class ActivityUtils {

    public static void setDisplayUpNavigation(AppCompatActivity activity, boolean status) {
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(status);
        }
    }
}
