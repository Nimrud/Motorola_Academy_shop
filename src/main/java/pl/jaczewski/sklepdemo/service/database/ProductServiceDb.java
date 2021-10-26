package pl.jaczewski.sklepdemo.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaczewski.sklepdemo.repository.database.ProductDaoDB;
import pl.jaczewski.sklepdemo.model.Product;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Service
public class ProductServiceDb {

    @Autowired
    private ProductDaoDB productDaoDB;

    public void create(Product product) {
        productDaoDB.save(product);
    }

    public void update(Product product) {
        productDaoDB.save(product);
    }

    public void delete(long id) {
        productDaoDB.deleteById(id);
    }

    public List<Product> findAll() {
        return productDaoDB.findAll();
    }

    public Product getProductById(long id) {
        return productDaoDB.findProductById(id);
    }

    public Product getProductByName(String name) {
        return productDaoDB.findProductByName(name);
    }

    // do sprawdzenia
    public List<Product.Category> getAllCategories() {
        List<Product.Category> categories = new ArrayList<>();
        for (Product.Category c : EnumSet.allOf(Product.Category.class)) {
            categories.add(c);
        }
        return categories;
    }
}
