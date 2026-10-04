package com.comphand.bo.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.comphand.bo.ProductBo;
import com.comphand.dao.ProductDao;
import com.comphand.model.Product;

public class ProductBoImpl implements ProductBo {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        return productDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productDao.findById(id);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        return productDao.update(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productDao.delete(id);
    }
}
