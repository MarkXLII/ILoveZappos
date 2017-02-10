package in.swapnilbhoite.projects.ilovezappos;

import java.util.ArrayList;
import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.models.Product;

public class Cart {

    private static Cart ourInstance = new Cart();
    private static List<Product> productList;

    private Cart() {
        productList = new ArrayList<>();
    }

    public static synchronized Cart getInstance() {
        return ourInstance;
    }


    public boolean contains(Product product) {
        return productList.contains(product);
    }

    public void addItem(Product product) {
        productList.add(product);
    }

    public int getCount() {
        return productList.size();
    }
}
