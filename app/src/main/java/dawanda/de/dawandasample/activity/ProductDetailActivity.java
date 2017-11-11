package dawanda.de.dawandasample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.OnClick;
import dawanda.de.dawandasample.Constants;
import dawanda.de.dawandasample.R;
import dawanda.de.dawandasample.model.Price;
import dawanda.de.dawandasample.model.Product;

/**
 * Display details of a given product
 */

public class ProductDetailActivity extends BaseActivity {
    public final static String PRODUCT = "product";
    Product mProduct;

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.product_title)
    TextView mProductTitle;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    @BindView(R.id.shop_title)
    TextView mShopTitle;

    @BindView(R.id.seller_username)
    TextView mSellerUsername;

    @BindView(R.id.seller_rating)
    RatingBar mSellerRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_product_details);
        }

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(PRODUCT)) {
                mProduct = (Product) getIntent().getExtras().getSerializable(PRODUCT);
            }
        } else if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(PRODUCT)) {
                mProduct = (Product) savedInstanceState.getSerializable(PRODUCT);
            }
        }

        init();
    }

    private void init() {
        if (mProduct.getDefaultImage() != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.bg_placeholder)
                    .error(R.drawable.bg_placeholder)
                    .fitCenter();
            Glide.with(getBaseContext())
                    .load(Constants.HTTPS_URL_PREFIX + mProduct.getDefaultImage().getProductL())
                    .apply(requestOptions)
                    .into(mProductImage);
        }

        mProductTitle.setText(mProduct.getTitle());

        Price basePrice = mProduct.getPrice();
        if (basePrice != null) {
            mProductPrice.setText(String.format(getBaseContext().getString(R.string.price_format),
                    basePrice.getCurrency(),
                    basePrice.getSymbol(),
                    (float) basePrice.getCents()/100));
        }

        if (mProduct.getShop() != null) {
            mShopTitle.setText(mProduct.getShop().getTitle());
        }

        if (mProduct.getSeller() != null) {
            mSellerUsername.setText(mProduct.getSeller().getUsername());
            mSellerRating.setRating(mProduct.getSeller().getRating());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PRODUCT, mProduct);
    }

    // setup toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        if (mProduct != null) {
            getMenuInflater().inflate(R.menu.menu_product, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // handle back button navigation by default
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_share:
                shareItemUrl();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.product_image)
    public void onImageClick() {
        if (mProduct == null || mProduct.getDefaultImage() == null || mProduct.getDefaultImage().getProductL() == null) return;
        showViewImageActivity();
    }

    private void shareItemUrl() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, mProduct.getTitle());
        shareIntent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.share_format),
                mProduct.getTitle(),
                String.valueOf(mProduct.getId()),
                mProduct.getSlug()));

        startActivity(shareIntent);
    }

    private void showViewImageActivity() {
        Intent intent = new Intent(this, ViewImageActivity.class);
        intent.putExtra(PRODUCT, mProduct);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
