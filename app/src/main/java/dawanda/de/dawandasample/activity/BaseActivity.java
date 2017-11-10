package dawanda.de.dawandasample.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import dagger.android.AndroidInjection;
import dawanda.de.dawandasample.R;

import static java.net.Proxy.Type.HTTP;

/**
 * Created by natashalotra on 2017/11/06.
 */

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
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
