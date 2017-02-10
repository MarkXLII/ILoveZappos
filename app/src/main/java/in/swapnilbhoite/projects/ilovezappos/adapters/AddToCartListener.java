package in.swapnilbhoite.projects.ilovezappos.adapters;

import in.swapnilbhoite.projects.ilovezappos.models.Product;

public interface AddToCartListener {

    void cartItemUpdated(boolean added, Product product);
}
