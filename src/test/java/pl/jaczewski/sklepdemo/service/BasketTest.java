package pl.jaczewski.sklepdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jaczewski.sklepdemo.database.BasketDAO;
import pl.jaczewski.sklepdemo.model.ItemInBasket;
import pl.jaczewski.sklepdemo.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {

    @BeforeEach
    private void setup() {
        BasketDAO basketDAO = new BasketDAO();
        basketDAO.addItem(new ItemInBasket(new Product("lampion", "", new BigDecimal("45"), Product.Category.HOUSEHOLD_GOODS, 20), 4));
        basketDAO.addItem(new ItemInBasket(new Product("oranżada", "", new BigDecimal("4.25"), Product.Category.FOOD, 20), 5));
    }

    @Test
    public void testGetTotalPrice_over200priceReduction() {
        BasketDAO basketDAO = new BasketDAO();
        basketDAO.addItem(new ItemInBasket(new Product("lampion", "", new BigDecimal("45"), Product.Category.HOUSEHOLD_GOODS, 20), 4));
        basketDAO.addItem(new ItemInBasket(new Product("oranżada", "", new BigDecimal("4.25"), Product.Category.FOOD, 20), 5));

        BigDecimal result = basketDAO.finalPrice();

        assertThat(result).isEqualByComparingTo(new BigDecimal(191.19).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testGetTotalPrice_over500priceReduction() {
        BasketDAO basketDAO = new BasketDAO();
        basketDAO.addItem(new ItemInBasket(new Product("laptop", "", new BigDecimal("2999.99"), Product.Category.HOUSEHOLD_GOODS, 2), 1));

        BigDecimal result = basketDAO.finalPrice();

        assertThat(result).isEqualByComparingTo(new BigDecimal(2699.99).setScale(2, RoundingMode.HALF_UP));
    }
}
