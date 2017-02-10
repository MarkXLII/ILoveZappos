package in.swapnilbhoite.projects.ilovezappos;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.adapters.SearchItemDecoration;
import in.swapnilbhoite.projects.ilovezappos.adapters.SearchResultAdapter;
import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkController;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkControllerImpl;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkResponse;

public class MainActivity extends AppCompatActivity
        implements NetworkResponse<List<Product>>,
        MenuItemCompat.OnActionExpandListener, SearchView.OnQueryTextListener {

    private RecyclerView recyclerViewSearchResults;
    private NetworkController networkController;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        recyclerViewSearchResults =
                (RecyclerView) findViewById(R.id.recycler_view_search_results);
        SearchItemDecoration itemDecoration =
                new SearchItemDecoration(MainActivity.this, R.dimen.item_offset);
        recyclerViewSearchResults.addItemDecoration(itemDecoration);
        recyclerViewSearchResults.setHasFixedSize(true);
        recyclerViewSearchResults.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        searchView.setOnQueryTextListener(this);
        searchItem.expandActionView();
        return true;
    }

    @Override
    public void onSuccess(List<Product> response) {
        SearchResultAdapter searchResultAdapter =
                new SearchResultAdapter(response);
        recyclerViewSearchResults.setAdapter(searchResultAdapter);
        Log.d("SWAP", response.toString());
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("SWAP", throwable.getMessage());
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
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
