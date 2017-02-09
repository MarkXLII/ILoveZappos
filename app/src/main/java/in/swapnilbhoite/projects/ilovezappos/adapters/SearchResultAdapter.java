package in.swapnilbhoite.projects.ilovezappos.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.databinding.WidgetSearchResultBinding;
import in.swapnilbhoite.projects.ilovezappos.models.Product;

public class SearchResultAdapter extends RecyclerView.Adapter {

    private static final int VIEW_HOLDER_TYPE_SEARCH_RESULT = 0;
    private final List<Product> productList;

    public SearchResultAdapter(List<Product> productList) {
        if (productList != null) {
            this.productList = new ArrayList<>(productList);
        } else {
            this.productList = new ArrayList<>();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WidgetSearchResultBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
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

        ProductViewHolder(WidgetSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            binding.setProduct(product);
            binding.executePendingBindings();
        }
    }
}
