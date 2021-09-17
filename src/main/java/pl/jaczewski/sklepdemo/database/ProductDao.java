package pl.jaczewski.sklepdemo.database;

import org.springframework.stereotype.Component;
import pl.jaczewski.sklepdemo.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {

    private List<Product> products = Arrays.asList(
            new Product("Mydło", "Najlepsze mydełko pod słońcem, super pianka, boski zapach", new BigDecimal("25.00"), Product.Category.DOMESTIC_DETERGENTS, 10),
            new Product("Masło", "Niezdrowe, same tłuszcze nasycone", new BigDecimal("6.99"), Product.Category.FOOD, 20),
            new Product("Chleb", "Razowy, chrupiący, pyszny", new BigDecimal("5.50"), Product.Category.FOOD, 15),
            new Product("Colt AR-15", "Półautomatyczny, magazynek na 25 nabojów, Made in USA", new BigDecimal("9999.99"), Product.Category.WEAPONS, 1),
            new Product("Wyciskarka do czosnku", "Żadna inna tak nie wyciska", new BigDecimal("20.00"), Product.Category.HOUSEHOLD_GOODS, 2),
            new Product("Piwo Okocim", "Okocim spojrzeniu", new BigDecimal("5.50"), Product.Category.ALCOHOL, 40),
            new Product("Lalka", "Chińska, na baterie, wydaje odgłosy", new BigDecimal("49.99"), Product.Category.TOYS, 2));

    public List<Product> all() {
        return products;
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
}
