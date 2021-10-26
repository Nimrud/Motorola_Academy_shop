package pl.jaczewski.sklepdemo.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.jaczewski.sklepdemo.repository.ProductDao;
import pl.jaczewski.sklepdemo.model.Product;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao = new ProductDao();

    @Override
    public void addProduct(@NonNull Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void removeProduct(int id) {
        productDao.removeProduct(id);
    }

    @Override
    public Product getProduct(int id) {
        return productDao.findProductById(id);
    }

    @Override
    public Product getProduct(String name) {
        return productDao.findProductByName(name);
    }

    @Override
    public void updateProduct(@NonNull Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.allProducts();
    }


    public List<Product> getProductsForEveryone() {
        return productDao.allAllowedUnder18yo();
    }

    @Override
    public List<Product.Category> getCategories() {
        return productDao.categories();
    }

    @Override
    public Map<String, String> showAvailability() {
        return productDao.productAvailability();
    }
}
