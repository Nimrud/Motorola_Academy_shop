package pl.jaczewski.sklepdemo.product;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    public Product byName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }
}
