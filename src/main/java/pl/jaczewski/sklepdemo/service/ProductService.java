package pl.jaczewski.sklepdemo.service;

import pl.jaczewski.sklepdemo.database.ProductDao;
import pl.jaczewski.sklepdemo.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void removeProduct(int id);
    Product getProduct(int id);
    Product getProduct(String name);
    void updateProduct(Product product);
    List<Product> getData();
    List<Product> getDataForEveryone();
    List<Product.Category> getCategories();
}
