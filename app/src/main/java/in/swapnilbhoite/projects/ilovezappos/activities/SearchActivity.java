package in.swapnilbhoite.projects.ilovezappos.activities;

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

import in.swapnilbhoite.projects.ilovezappos.R;
import in.swapnilbhoite.projects.ilovezappos.adapters.OnResultClickedListener;
import in.swapnilbhoite.projects.ilovezappos.adapters.SearchItemDecoration;
import in.swapnilbhoite.projects.ilovezappos.adapters.SearchResultAdapter;
import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkController;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkControllerImpl;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkResponse;

public class SearchActivity extends AppCompatActivity
        implements NetworkResponse<List<Product>>,
        MenuItemCompat.OnActionExpandListener, SearchView.OnQueryTextListener, OnResultClickedListener {

    private RecyclerView recyclerViewSearchResults;
    private NetworkController networkController;
    private SearchView searchView;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUpToolbar();
        setUpRecyclerView();
        networkController = new NetworkControllerImpl();
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
        View cartView = MenuItemCompat.getActionView(cartItem);
        TextView count = (TextView) cartView.findViewById(R.id.text_view_cart_item_count);
        count.setText("5");
        return true;
    }

    @Override
    public void onSuccess(List<Product> response) {
        progressBar.setVisibility(View.GONE);
        SearchResultAdapter searchResultAdapter =
                new SearchResultAdapter(response, this);
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
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "image_view_thumb");
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
}
