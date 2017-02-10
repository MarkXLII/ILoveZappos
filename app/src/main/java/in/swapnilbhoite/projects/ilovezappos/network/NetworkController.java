package in.swapnilbhoite.projects.ilovezappos.network;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.models.ProductDetail;

public interface NetworkController {

    void search(@NonNull String term, NetworkResponse<List<Product>> listener);

    void getProductDetails(@NotNull String productId, NetworkResponse<ProductDetail> listener);
}
