package pl.jaczewski.sklepdemo.service;

import lombok.NonNull;
import pl.jaczewski.sklepdemo.database.ProductDao;
import pl.jaczewski.sklepdemo.model.Product;

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
        return productDao.byId(id);
    }

    @Override
    public Product getProduct(String name) {
        return productDao.byName(name);
    }

    @Override
    public void updateProduct(@NonNull Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public ProductDao getData() {
        return productDao;
    }
}
