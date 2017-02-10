package in.swapnilbhoite.projects.ilovezappos.adapters;

import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.R;
import in.swapnilbhoite.projects.ilovezappos.databinding.WidgetSearchResultBinding;
import in.swapnilbhoite.projects.ilovezappos.models.Product;

public class SearchResultAdapter extends RecyclerView.Adapter {

    private static final int VIEW_HOLDER_TYPE_SEARCH_RESULT = 0;
    private static OnResultClickedListener resultClickedListener;
    private final List<Product> productList;

    public SearchResultAdapter(List<Product> productList, OnResultClickedListener resultClickedListener) {
        if (productList != null) {
            this.productList = new ArrayList<>(productList);
        } else {
            this.productList = new ArrayList<>();
        }
        this.resultClickedListener = resultClickedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WidgetSearchResultBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.widget_search_result, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ProductViewHolder) holder).bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_HOLDER_TYPE_SEARCH_RESULT;
    }

    private static class ProductViewHolder extends RecyclerView.ViewHolder {

        private final WidgetSearchResultBinding binding;
        private final ImageView imageViewThumb;
        private final TextView textViewOriginalPrice;
        private final TextView textViewPercentOff;

        ProductViewHolder(WidgetSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.imageViewThumb = (ImageView) binding.getRoot()
                    .findViewById(R.id.image_view_thumb);
            this.textViewOriginalPrice = (TextView) binding.getRoot().findViewById(R.id.text_view_original_price);
            this.textViewPercentOff = (TextView) binding.getRoot().findViewById(R.id.text_view_percent_off);

        }

        void bind(final Product product) {
            Picasso.with(imageViewThumb.getContext())
                    .load(product.getThumbnailImageUrl())
                    .into(imageViewThumb);
            binding.setProduct(product);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (resultClickedListener != null) {
                        resultClickedListener.resultClicked(product, v);
                    }
                }
            });
            if (product.getPercentOff().equals("0%")) {
                textViewOriginalPrice.setVisibility(View.GONE);
                textViewPercentOff.setVisibility(View.GONE);
            } else {
                textViewOriginalPrice
                        .setPaintFlags(textViewOriginalPrice.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                textViewPercentOff.setVisibility(View.VISIBLE);
                String off = product.getPercentOff() + " OFF!";
                textViewPercentOff.setText(off);
            }
        }
    }
}
