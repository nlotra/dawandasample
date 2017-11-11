package dawanda.de.dawandasample.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;

import butterknife.BindView;
import butterknife.OnClick;
import dawanda.de.dawandasample.Constants;
import dawanda.de.dawandasample.R;
import dawanda.de.dawandasample.model.Product;

/**
 * Display a given image with options to pan and zoom
 */

public class ViewImageActivity extends BaseActivity {
    Product mProduct;

    @BindView(R.id.big_image)
    BigImageView mImageViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BigImageViewer.initialize(GlideImageLoader.with(getApplicationContext()));

        setContentView(R.layout.activity_view_image);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(ProductDetailActivity.PRODUCT)) {
                mProduct = (Product) getIntent().getExtras().getSerializable(ProductDetailActivity.PRODUCT);
            }
        } else if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(ProductDetailActivity.PRODUCT)) {
                mProduct = (Product) savedInstanceState.getSerializable(ProductDetailActivity.PRODUCT);
            }
        }

        init();
    }

    public void init() {
        if (mProduct == null || mProduct.getDefaultImage() == null || mProduct.getDefaultImage().getProductL() == null) {
            Toast.makeText(getBaseContext(), "No image available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mProduct.getTitle());
        }

        mImageViewer.setProgressIndicator(new ProgressPieIndicator());
        mImageViewer.showImage(Uri.parse(Constants.HTTPS_URL_PREFIX + mProduct.getDefaultImage().getProductL()));
    }

    @OnClick(R.id.big_image)
    public void onImageClick() {
        if (getSupportActionBar() == null) return;

        if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ProductDetailActivity.PRODUCT, mProduct);
    }
}
