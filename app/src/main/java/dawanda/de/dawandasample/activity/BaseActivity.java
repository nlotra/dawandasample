package dawanda.de.dawandasample.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dawanda.de.dawandasample.R;

/**
 * Superclass for activities in the app
 * Handles some things by default such as toolbar, butterknife and dagger setup
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        ButterKnife.bind(this);
        setupToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        setupToolbar();
    }

    private void setupToolbar() {
        // Handle default toolbar for all activities here (if present)
        if (mToolbar != null) {
            mToolbar.setTitle("");
            mToolbar.setContentInsetStartWithNavigation(0);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // handle back button navigation by default
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
