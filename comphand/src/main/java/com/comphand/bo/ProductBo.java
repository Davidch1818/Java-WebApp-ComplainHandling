package com.comphand.bo;

import java.util.List;

import com.comphand.model.Product;

/**
 * Business Object contract for Product. See UserBo for the note on why
 * this interface must be byte-for-byte identical in both projects.
 */
public interface ProductBo {

    List<Product> listProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
