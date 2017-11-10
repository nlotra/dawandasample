package dawanda.de.dawandasample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dawanda.de.dawandasample.Constants;
import dawanda.de.dawandasample.R;
import dawanda.de.dawandasample.listener.OnItemSelectedListener;
import dawanda.de.dawandasample.model.Category;

/**
 * Created by natashalotra on 2017/11/09.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    List<Category> mCategories;
    OnItemSelectedListener mListener;

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = mCategories.get(position);
        holder.categoryTitle.setText(category.getName());
        Glide.with(holder.categoryImage.getContext())
                .load(Constants.HTTPS_URL_PREFIX + category.getImageUrl())
                .into(holder.categoryImage);
        holder.categoryImage.setOnClickListener((view) -> mListener.onItemSelected(category));
    }

    @Override
    public int getItemCount() {
        return mCategories == null ? 0 : mCategories.size();
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.mListener = listener;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_image)
        ImageView categoryImage;

        @BindView(R.id.category_title)
        TextView categoryTitle;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
