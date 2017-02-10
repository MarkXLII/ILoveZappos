package in.swapnilbhoite.projects.ilovezappos;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import in.swapnilbhoite.projects.ilovezappos.databinding.ActivityProductDetailBinding;
import in.swapnilbhoite.projects.ilovezappos.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private static Product PRODUCT;

    public static void setProduct(Product product) {
        PRODUCT = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpContentView();
        setUpToolbar();
    }

    private void setUpContentView() {
        ActivityProductDetailBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_product_detail);
        binding.setProduct(PRODUCT);
        binding.executePendingBindings();
        ImageView view = (ImageView) findViewById(R.id.image_view_thumb);
        Picasso.with(this)
                .load(PRODUCT.getThumbnailImageUrl())
                .into(view);
    }

    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        myToolbar.setTitle(PRODUCT.getBrandName());
    }
}
