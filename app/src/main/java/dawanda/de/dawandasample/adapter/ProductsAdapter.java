package dawanda.de.dawandasample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dawanda.de.dawandasample.Constants;
import dawanda.de.dawandasample.R;
import dawanda.de.dawandasample.listener.OnItemSelectedListener;
import dawanda.de.dawandasample.model.Price;
import dawanda.de.dawandasample.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private List<Product> mProducts;
    private OnItemSelectedListener mListener;

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.product = product;

        Context context = holder.productImage.getContext();

        if (product.getDefaultImage() != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.bg_placeholder)
                    .error(R.drawable.bg_placeholder)
                    .centerCrop();
            Glide.with(context)
                    .load(Constants.HTTPS_URL_PREFIX + product.getDefaultImage().getProductL())
                    .apply(requestOptions)
                    .into(holder.productImage);
        }

        holder.productTitle.setText(product.getTitle());

        holder.productBadge.setVisibility(product.getBadge() != null ? View.VISIBLE : View.GONE);
        holder.productBadge.setText(product.getBadge());

        Price basePrice = product.getPrice();
        if (basePrice != null) {
            holder.productPrice.setText(String.format(context.getString(R.string.price_format),
                    basePrice.getCurrency(),
                    basePrice.getSymbol(),
                    (float) basePrice.getCents()/100));
        }
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        Product product;

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_title)
        TextView productTitle;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.product_badge)
        TextView productBadge;

        @OnClick(R.id.product_container)
        public void onClick() {
            if (mListener == null) return;
            mListener.onItemSelected(product);
        }

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.mListener = listener;
    }

    public void setProducts(List<Product> products) {
        this.mProducts = products;
        notifyDataSetChanged();
    }
}
