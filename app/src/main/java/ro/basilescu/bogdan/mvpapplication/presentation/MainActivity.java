package ro.basilescu.bogdan.mvpapplication.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ro.basilescu.bogdan.mvpapplication.R;
import ro.basilescu.bogdan.mvpapplication.presentation.usecases.main.MainFragment;
import ro.basilescu.bogdan.mvpapplication.presentation.utils.NavigationUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindString(R.string.main_fragment_tag)
    String mainFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        NavigationUtils.addFragmentToActivity(getSupportFragmentManager(),
                new MainFragment(), mainFragmentTag,
                R.id.fragment_container);
    }
}
