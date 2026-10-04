package com.comphand.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.comphand.dao.ProductDao;
import com.comphand.model.Product;

public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> findAll() {
        return currentSession().createQuery("from Product", Product.class).list();
    }

    @Override
    public Product findById(Long id) {
        return currentSession().get(Product.class, id);
    }

    @Override
    public Product save(Product product) {
        currentSession().persist(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        return (Product) currentSession().merge(product);
    }

    @Override
    public void delete(Long id) {
        Product product = currentSession().get(Product.class, id);
        if (product != null) {
            currentSession().remove(product);
        }
    }
}
