package pl.jaczewski.sklepdemo.service;

import pl.jaczewski.sklepdemo.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void addProduct(Product product);
    void removeProduct(int id);
    Product getProduct(int id);
    Product getProduct(String name);
    void updateProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductsForEveryone();
    List<Product.Category> getCategories();
    Map<String, String> showAvailability();
}
