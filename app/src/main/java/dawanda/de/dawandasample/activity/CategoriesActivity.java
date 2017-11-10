package dawanda.de.dawandasample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import dawanda.de.dawandasample.R;
import dawanda.de.dawandasample.adapter.CategoriesAdapter;
import dawanda.de.dawandasample.model.Category;
import dawanda.de.dawandasample.network.NetworkManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by natashalotra on 2017/11/06.
 */

public class CategoriesActivity extends BaseActivity {
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    CategoriesAdapter mAdapter;

    @Inject
    NetworkManager mNetworkManager;

    CompositeDisposable mSubscriptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mSubscriptions = new CompositeDisposable();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setLogo(R.drawable.ic_dawanda_tag_toolbar);
        init();
    }

    public void init() {
        Log.d("CategoriesActivity", "init");
        mAdapter = new CategoriesAdapter();
        mAdapter.setOnItemSelectedListener((item) -> {
            if (item instanceof Category)
                showProductsActivity((Category) item);
        });
        mRecyclerView.setAdapter(mAdapter);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getBaseContext(), FlexDirection.ROW, FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        mRecyclerView.setLayoutManager(layoutManager);

        FlexboxItemDecoration flexboxItemDecoration = new FlexboxItemDecoration(getBaseContext());
        flexboxItemDecoration.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.divider));
        mRecyclerView.addItemDecoration(flexboxItemDecoration);

        mSwipeRefreshLayout.setOnRefreshListener(this::loadCategories);

        loadCategories();
    }

    private void loadCategories() {
        setRefreshing(true);
        mSubscriptions.add(mNetworkManager.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> setRefreshing(false))
                .subscribe((categories) -> mAdapter.setCategories(categories),
                        (throwable) -> {
                            if (throwable instanceof IOException) {
                                // network error
                            }
                        }
                ));
    }

    private void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(refreshing));
    }

    private void showProductsActivity(Category category) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra(ProductsActivity.CATEGORY, category);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null) mSubscriptions.dispose();
    }
}
