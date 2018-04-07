package ice.mobarak.test2.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ice.mobarak.test2.R;
import ice.mobarak.test2.ui.dashboard.DashboardFragment;
import ice.mobarak.test2.utils.Constants;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new DashboardFragment(), Constants.CURRENT_FRAGMENT)
                    .commit();
        }
    }

    /**
     * Invoke to set visibility of back arrow
     *
     * @param isShow true|false
     */
    public void setBackArrowVisibility(boolean isShow) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
        }
    }

    /**
     * Set title text
     *
     * @param title String
     */
    public void setTitleText(String title) {
        if (getSupportActionBar() == null || title == null) {
            return;
        }
        getSupportActionBar().setTitle(title);
    }
}
