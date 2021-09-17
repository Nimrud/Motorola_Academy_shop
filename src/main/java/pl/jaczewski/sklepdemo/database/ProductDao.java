package pl.jaczewski.sklepdemo.database;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import pl.jaczewski.sklepdemo.model.Product;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ProductDao {

    // tymczasowe pole
    // TODO: po dodaniu DB zmienić na AUTO_INCREMENT
    private static int idValue = 1;
    private final List<Product> products = new ArrayList<>();

    public ProductDao() {
        addProduct(new Product("Mydło", "Najlepsze mydełko pod słońcem, super pianka, boski zapach", new BigDecimal("25.00"), Product.Category.DOMESTIC_DETERGENTS, 10));
        addProduct(new Product("Masło", "Niezdrowe, same tłuszcze nasycone", new BigDecimal("6.99"), Product.Category.FOOD, 20));
        addProduct(new Product("Chleb", "Razowy, chrupiący, pyszny", new BigDecimal("5.50"), Product.Category.FOOD, 15));
        addProduct(new Product("Colt AR-15", "Półautomatyczny, magazynek na 25 nabojów, Made in USA", new BigDecimal("9999.99"), Product.Category.WEAPONS, 1));
        addProduct(new Product("Wyciskarka do czosnku", "Żadna inna tak nie wyciska", new BigDecimal("20.00"), Product.Category.HOUSEHOLD_GOODS, 2));
        addProduct(new Product("Piwo Okocim", "Okocim spojrzeniu", new BigDecimal("5.50"), Product.Category.ALCOHOL, 40));
        addProduct(new Product("Lalka", "Chińska, na baterie, wydaje odgłosy", new BigDecimal("49.99"), Product.Category.TOYS, 2));
        addProduct(new Product("Nałęczowianka 1,5 litra", "Woda mineralna", new BigDecimal("1.99"), Product.Category.FOOD, 20));
    }

    public List<Product> all() {
        return Collections.unmodifiableList(products);
    }

    public List<Product> allAllowed() {
        List<Product> allUnder18 = new ArrayList<>();
        for (Product p : products) {
            if ((p.getCategory().equals(Product.Category.ALCOHOL)) || (p.getCategory().equals(Product.Category.WEAPONS)) || (p.getCategory().equals(Product.Category.DRUGS))) {

            } else {
                allUnder18.add(p);
            }
        }
        return allUnder18;
    }

    public List<Product> allOver18() {
        List<Product> allOver18 = new ArrayList<>();
        for (Product p : products) {
            if ((p.getCategory().equals(Product.Category.ALCOHOL)) || (p.getCategory().equals(Product.Category.WEAPONS)) || (p.getCategory().equals(Product.Category.DRUGS))) {
                allOver18.add(p);
            }
        }
        return allOver18;
    }

    public Product byName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }

    public Product byId(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(@NonNull Product product) {
        if(!products.contains(product)) {
            product.setId(idValue);
            products.add(product);
            idValue++;
        }
    }

    public void removeProduct(int id) {
        ListIterator<Product> productIterator = products.listIterator();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            if (product.getId() == id) {
                productIterator.remove();
                break;
            }
        }
    }

    public void updateProduct(@NonNull Product productToUpdate) {
        ListIterator<Product> productIterator = products.listIterator();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            if (product.equals(productToUpdate)) {
                productIterator.set(productToUpdate);
                break;
            }
        }
    }

    public List<Product.Category> categories() {
        List<Product.Category> allCategories = new ArrayList<>();
        for (Product.Category c : EnumSet.allOf(Product.Category.class)) {
            allCategories.add(c);
        }
        return allCategories;
    }
}
