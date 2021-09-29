package pl.jaczewski.sklepdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jaczewski.sklepdemo.database.BasketDAO;
import pl.jaczewski.sklepdemo.model.ItemInBasket;
import pl.jaczewski.sklepdemo.model.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {

    private BasketDAO basketDAO;

    @BeforeEach
    private void setup() {
        basketDAO = new BasketDAO();
        basketDAO.addItem(new ItemInBasket(new Product("lampion", "", BigDecimal.valueOf(45), Product.Category.HOUSEHOLD_GOODS, 20), 4));
        basketDAO.addItem(new ItemInBasket(new Product("oranżada", "", BigDecimal.valueOf(4.25), Product.Category.FOOD, 20), 5));
    }

    @Test
    public void testGetTotalPrice_over200priceReduction() {
        BigDecimal result = basketDAO.finalPrice();

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(191.19));
    }

    @Test
    public void testGetTotalPrice_over500priceReduction() {
        basketDAO.addItem(new ItemInBasket(new Product("laptop", "", BigDecimal.valueOf(2999.99), Product.Category.HOUSEHOLD_GOODS, 2), 1));

        BigDecimal result = basketDAO.finalPrice();

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(2881.12));
    }
}
