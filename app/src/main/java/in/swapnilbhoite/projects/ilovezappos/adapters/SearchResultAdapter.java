package in.swapnilbhoite.projects.ilovezappos.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.Cart;
import in.swapnilbhoite.projects.ilovezappos.R;
import in.swapnilbhoite.projects.ilovezappos.databinding.WidgetSearchResultBinding;
import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.models.Style;

public class SearchResultAdapter extends RecyclerView.Adapter {

    private static final int VIEW_HOLDER_TYPE_SEARCH_RESULT = 0;
    private static AddToCartListener addToCartListener;
    private static OnResultClickedListener resultClickedListener;
    private final List<Product> productList;

    public SearchResultAdapter(List<Product> productList,
                               OnResultClickedListener resultClickedListener,
                               AddToCartListener addToCartListener) {
        if (productList != null) {
            this.productList = new ArrayList<>(productList);
        } else {
            this.productList = new ArrayList<>();
        }
        SearchResultAdapter.resultClickedListener = resultClickedListener;
        SearchResultAdapter.addToCartListener = addToCartListener;
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
        private final ImageButton imageButtonAddToCart;

        ProductViewHolder(WidgetSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.imageViewThumb = (ImageView) binding.getRoot()
                    .findViewById(R.id.image_view_thumb);
            this.textViewOriginalPrice = (TextView) binding.getRoot().findViewById(R.id.text_view_original_price);
            this.textViewPercentOff = (TextView) binding.getRoot().findViewById(R.id.text_view_percent_off);
            this.imageButtonAddToCart = (ImageButton) binding.getRoot().findViewById(R.id.image_button_add_to_cart);
        }

        void bind(final Product product) {
            loadThumbnail(product, imageViewThumb, imageViewThumb.getContext());
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
            if (Cart.getInstance().contains(product)) {
                imageButtonAddToCart.setImageResource(R.drawable.ic_remove_shopping_cart_white_24dp);
            } else {
                imageButtonAddToCart.setImageResource(R.drawable.ic_add_shopping_cart_white_24dp);
            }
            imageButtonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addToCartListener != null) {
                        if (!Cart.getInstance().contains(product)) {
                            addToCartListener.cartItemUpdated(true, product);
                        } else {
                            addToCartListener.cartItemUpdated(false, product);
                        }
                        v.refreshDrawableState();
                    }
                }
            });
        }

        private void loadThumbnail(Product product, ImageView imageView, Context context) {
            if (product.getStyles() != null) {
                for (Style style : product.getStyles()) {
                    if (style.getStyleId().equals(product.getStyleId())) {
                        Picasso.with(context).invalidate(product.getThumbnailImageUrl());
                        Picasso.with(context)
                                .load(style.getImageUrl())
                                .into(imageView);
                        break;
                    }
                }
            } else {
                Picasso.with(context)
                        .load(product.getThumbnailImageUrl())
                        .into(imageView);
            }
        }
    }
}
