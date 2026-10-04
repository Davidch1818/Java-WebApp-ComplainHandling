package com.comphand.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.comphand.bo.ProductBo;
import com.comphand.model.Product;

public class ProductViewModel {

    @WireVariable
    private ProductBo productBo;

    private List<Product> products;
    private Product newProduct = new Product();
    private String statusMessage = "";

    @Init
    public void init() {
        loadProducts();
    }

    private void loadProducts() {
        products = productBo.listProducts();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @Command
    @NotifyChange({"products", "newProduct", "statusMessage"})
    public void createProduct() {
        try {
            productBo.createProduct(newProduct);
            statusMessage = "Product created.";
            newProduct = new Product();
            loadProducts();
        } catch (Exception e) {
            statusMessage = "Could not create product: " + e.getMessage();
        }
    }

    @Command
    @NotifyChange({"products", "statusMessage"})
    public void deleteProduct(@BindingParam("product") Product product) {
        try {
            productBo.deleteProduct(product.getId());
            statusMessage = "Product deleted.";
            loadProducts();
        } catch (Exception e) {
            statusMessage = "Could not delete product: " + e.getMessage();
        }
    }
}
