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
import in.swapnilbhoite.projects.ilovezappos.models.ProductDetail;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkController;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkControllerImpl;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkResponse;

public class ProductDetailActivity extends AppCompatActivity implements NetworkResponse<ProductDetail> {

    private static Product PRODUCT;
    private NetworkController networkController;
    private ImageView thumbnail;

    public static void setProduct(Product product) {
        PRODUCT = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkController = new NetworkControllerImpl();
        setUpContentView();
        setUpToolbar();
    }

    private void setUpContentView() {
        ActivityProductDetailBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_product_detail);
        binding.setProduct(PRODUCT);
        binding.executePendingBindings();
        this.thumbnail = (ImageView) findViewById(R.id.image_view_thumb);
        Picasso.with(this)
                .load(PRODUCT.getThumbnailImageUrl())
                .into(thumbnail);
        networkController.getProductDetails(PRODUCT.getProductId(), this);
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

    @Override
    public void onSuccess(ProductDetail response) {
        if (response != null && response.getProduct() != null && !response.getProduct().isEmpty()) {
            Product product = response.getProduct().get(0);
            PRODUCT.setBrandId(product.getBrandId());
            PRODUCT.setDefaultImageUrl(product.getDefaultImageUrl());
            PRODUCT.setDefaultProductUrl(product.getDefaultProductUrl());
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
    }
}
