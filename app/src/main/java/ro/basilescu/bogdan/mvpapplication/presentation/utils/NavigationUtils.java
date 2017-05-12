package ro.basilescu.bogdan.mvpapplication.presentation.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class NavigationUtils {

    public static final String ARGS_ID = "ARGS_ID";

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, String tag, int idContainer) {
        fragmentManager.beginTransaction()
                .add(idContainer, fragment, tag)
                .commit();
    }

    public static void replaceFragmentWith(FragmentManager fragmentManager, Fragment fragment, String tag, int idContainer) {
        fragmentManager.beginTransaction()
                .replace(idContainer, fragment)
                .addToBackStack(tag)
                .commit();
    }

    public static void popFromStackFragment(FragmentManager fragmentManager, String tag) {
        fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public static Fragment getInstanceWithArgument(Fragment fragment, int id) {
        Bundle args = new Bundle();
        args.putInt(ARGS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }
}
