package dawanda.de.dawandasample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import dawanda.de.dawandasample.R;
import dawanda.de.dawandasample.adapter.ProductsAdapter;
import dawanda.de.dawandasample.model.Category;
import dawanda.de.dawandasample.model.Product;
import dawanda.de.dawandasample.network.NetworkManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Display list of products in a given category
 */

public class ProductsActivity extends BaseActivity {
    public static final String CATEGORY = "category";
    private Category mCategory;

    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.error_message)
    TextView mErrorMessage;

    ProductsAdapter mAdapter;

    @Inject
    NetworkManager mNetworkManager;

    CompositeDisposable mSubscriptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(CATEGORY)) {
                mCategory = (Category) getIntent().getExtras().getSerializable(CATEGORY);
            }
        } else if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(CATEGORY)) {
                mCategory = (Category) savedInstanceState.getSerializable(CATEGORY);
            }
        }
        if (getSupportActionBar() != null && mCategory != null) {
            getSupportActionBar().setTitle(mCategory.getName());
        }

        mSubscriptions = new CompositeDisposable();

        init();
    }

    public void init() {
        Log.d("ProductsActivity", "init");
        mAdapter = new ProductsAdapter();
        mAdapter.setOnItemSelectedListener((item -> {
            if (item instanceof Product)
                showProductDetailActivity((Product) item);
        }));
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mSwipeRefreshLayout.setOnRefreshListener(this::loadProducts);

        loadProducts();
    }

    private void loadProducts() {
        if (mCategory == null) return;

        mErrorMessage.setVisibility(View.GONE);
        mAdapter.setProducts(null);

        setRefreshing(true);
        mSubscriptions.add(mNetworkManager.getProducts(mCategory.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> setRefreshing(false))
                .subscribe((products) -> mAdapter.setProducts(products),
                        (throwable) -> {
                            if (throwable instanceof IOException) {
                                mErrorMessage.setVisibility(View.VISIBLE);
                            }
                        }
                ));
    }

    private void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(refreshing));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CATEGORY, mCategory);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null) mSubscriptions.dispose();
    }

    private void showProductDetailActivity(Product product) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.PRODUCT, product);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
