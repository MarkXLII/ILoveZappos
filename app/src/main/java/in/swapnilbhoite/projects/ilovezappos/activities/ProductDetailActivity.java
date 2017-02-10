package in.swapnilbhoite.projects.ilovezappos.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.swapnilbhoite.projects.ilovezappos.Cart;
import in.swapnilbhoite.projects.ilovezappos.R;
import in.swapnilbhoite.projects.ilovezappos.databinding.ActivityProductDetailBinding;
import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.models.ProductDetail;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkController;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkControllerImpl;
import in.swapnilbhoite.projects.ilovezappos.network.NetworkResponse;

public class ProductDetailActivity extends AppCompatActivity implements NetworkResponse<ProductDetail>, View.OnClickListener {

    private static Product PRODUCT;
    private NetworkController networkController;
    private ImageView thumbnail;
    private FloatingActionButton fabAddToCart;

    public static void setProduct(Product product) {
        PRODUCT = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkController = new NetworkControllerImpl();
        setUpContentView();
        setUpToolbar();
        setUpFab();
    }

    private void setUpFab() {
        this.fabAddToCart = (FloatingActionButton) findViewById(R.id.fab_add_to_cart);
        if (Cart.getInstance().contains(PRODUCT)) {
            fabAddToCart.setImageResource(R.drawable.ic_remove_shopping_cart_white_24dp);
        } else {
            fabAddToCart.setImageResource(R.drawable.ic_add_shopping_cart_white_24dp);
        }
        fabAddToCart.setOnClickListener(this);
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

        TextView textViewOriginalPrice = (TextView) binding.getRoot().findViewById(R.id.text_view_original_price);
        TextView textViewPercentOff = (TextView) binding.getRoot().findViewById(R.id.text_view_percent_off);
        if (PRODUCT.getPercentOff().equals("0%")) {
            textViewOriginalPrice.setVisibility(View.GONE);
            textViewPercentOff.setVisibility(View.GONE);
        } else {
            textViewOriginalPrice
                    .setPaintFlags(textViewOriginalPrice.getPaintFlags()
                            | Paint.STRIKE_THRU_TEXT_FLAG);
            textViewPercentOff.setVisibility(View.VISIBLE);
            String off = PRODUCT.getPercentOff() + " OFF!";
            textViewPercentOff.setText(off);
        }
    }

    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(PRODUCT.getBrandName());
        }
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
    public void onClick(View v) {
        if (v.getId() == R.id.fab_add_to_cart) {
            final boolean added;
            if (!Cart.getInstance().contains(PRODUCT)) {
                Cart.getInstance().addItem(PRODUCT);
                added = true;
            } else {
                Cart.getInstance().removeItem(PRODUCT);
                added = false;
            }
            fabAddToCart.clearAnimation();
            ObjectAnimator animator;
            if (added) {
                animator = ObjectAnimator.ofFloat(fabAddToCart, View.ROTATION, 0, 360);
            } else {
                animator = ObjectAnimator.ofFloat(fabAddToCart, View.ROTATION, 360, 0);
            }
            animator.setDuration(500);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (added) {
                        fabAddToCart.setImageResource(R.drawable.ic_remove_shopping_cart_white_24dp);
                    } else {
                        fabAddToCart.setImageResource(R.drawable.ic_add_shopping_cart_white_24dp);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        }
    }
}
