package in.swapnilbhoite.projects.ilovezappos.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.Cart;
import in.swapnilbhoite.projects.ilovezappos.R;
import in.swapnilbhoite.projects.ilovezappos.adapters.AddToCartListener;
import in.swapnilbhoite.projects.ilovezappos.adapters.OnResultClickedListener;
import in.swapnilbhoite.projects.ilovezappos.adapters.SearchItemDecoration;
import in.swapnilbhoite.projects.ilovezappos.adapters.SearchResultAdapter;
import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkController;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkControllerImpl;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkResponse;

public class SearchActivity extends AppCompatActivity
        implements NetworkResponse<List<Product>>,
        MenuItemCompat.OnActionExpandListener,
        SearchView.OnQueryTextListener,
        OnResultClickedListener,
        AddToCartListener {

    private RecyclerView recyclerViewSearchResults;
    private NetworkController networkController;
    private SearchView searchView;
    private View progressBar;
    private TextView cartItemCount;
    private View cartView;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUpToolbar();
        setUpRecyclerView();
        networkController = new NetworkControllerImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadgeCount();
        if (searchResultAdapter != null) {
            searchResultAdapter.notifyDataSetChanged();
        }
    }

    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpRecyclerView() {
        progressBar = findViewById(R.id.progress_bar);
        recyclerViewSearchResults =
                (RecyclerView) findViewById(R.id.recycler_view_search_results);
        SearchItemDecoration itemDecoration =
                new SearchItemDecoration(SearchActivity.this, R.dimen.item_offset);
        recyclerViewSearchResults.addItemDecoration(itemDecoration);
        recyclerViewSearchResults.setHasFixedSize(true);
        recyclerViewSearchResults.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        searchView.setOnQueryTextListener(this);
        searchItem.expandActionView();

        MenuItem cartItem = menu.findItem(R.id.action_my_cart);
        this.cartView = MenuItemCompat.getActionView(cartItem);
        this.cartItemCount = (TextView) cartView.findViewById(R.id.text_view_cart_item_count);
        updateCartBadgeCount();
        return true;
    }

    private void updateCartBadgeCount() {
        updateCartBadgeCount(false);
    }

    private void updateCartBadgeCount(boolean animate) {
        if (cartItemCount == null) {
            return;
        }
        if (animate) {
            cartView.clearAnimation();
            ObjectAnimator animator = ObjectAnimator.ofFloat(cartView, View.ROTATION, 0, 360);
            animator.setDuration(200);
            animator.start();
        }
        if (Cart.getInstance().getCount() > 0) {
            cartItemCount.setText(String.valueOf(Cart.getInstance().getCount()));
            cartItemCount.setVisibility(View.VISIBLE);
        } else {
            cartItemCount.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccess(List<Product> response) {
        progressBar.setVisibility(View.GONE);
        this.searchResultAdapter = new SearchResultAdapter(response, this, this);
        recyclerViewSearchResults.setAdapter(searchResultAdapter);
    }

    @Override
    public void onFailure(Throwable throwable) {
        progressBar.setVisibility(View.GONE);
        throwable.printStackTrace();
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!query.isEmpty()) {
            networkController.search(query, this);
            searchView.clearFocus();
            progressBar.setVisibility(View.VISIBLE);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void resultClicked(Product product, View view) {
        ProductDetailActivity.setProduct(product);
        Intent intent = new Intent(this, ProductDetailActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, view.findViewById(R.id.image_view_thumb),
                        "image_view_thumb");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void cartItemUpdated(boolean added, Product product) {
        if (added) {
            Cart.getInstance().addItem(product);
        } else {
            Cart.getInstance().removeItem(product);
        }
        updateCartBadgeCount(true);
        if (searchResultAdapter != null) {
            searchResultAdapter.notifyDataSetChanged();
        }
    }
}
