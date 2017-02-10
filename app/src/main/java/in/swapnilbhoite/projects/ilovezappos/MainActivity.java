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
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.adapters.SearchItemDecoration;
import in.swapnilbhoite.projects.ilovezappos.adapters.SearchResultAdapter;
import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkController;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkControllerImpl;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSearchResults;
    private NetworkResponse<List<Product>> networkResponseSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpRecyclerView();
        NetworkController networkController = new NetworkControllerImpl();
        networkResponseSearch = new NetworkResponse<List<Product>>() {
            @Override
            public void onSuccess(List<Product> response) {
                SearchResultAdapter searchResultAdapter =
                        new SearchResultAdapter(response);
                int spacingInPixels = (int) TypedValue.
                        applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());
                SearchItemDecoration itemDecoration =
                        new SearchItemDecoration(MainActivity.this, R.dimen.item_offset);
                recyclerViewSearchResults.addItemDecoration(itemDecoration);
                recyclerViewSearchResults.setHasFixedSize(true);
                recyclerViewSearchResults.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                recyclerViewSearchResults.setAdapter(searchResultAdapter);
                Log.d("SWAP", response.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("SWAP", throwable.getMessage());
                throwable.printStackTrace();
            }
        };
        networkController.search("nike", networkResponseSearch);
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
        recyclerViewSearchResults.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        recyclerViewSearchResults.setLayoutManager(gridLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItemCompat.OnActionExpandListener expandListener =
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                };

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);

        return true;
    }
}
