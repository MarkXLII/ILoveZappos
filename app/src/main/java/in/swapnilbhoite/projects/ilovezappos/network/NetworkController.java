package in.swapnilbhoite.projects.ilovezappos.network;

import android.support.annotation.NonNull;

import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.models.Product;

public interface NetworkController {

    void search(@NonNull String term, NetworkResponse<List<Product>> listener);
}
