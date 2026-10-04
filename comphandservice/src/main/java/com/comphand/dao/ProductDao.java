package com.comphand.dao;

import java.util.List;

import com.comphand.model.Product;

public interface ProductDao {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Product product);

    void delete(Long id);
}
