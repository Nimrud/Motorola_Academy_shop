package pl.jaczewski.sklepdemo.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Basket {

    private List<ItemInBasket> products = new LinkedList<>();

    public List<ItemInBasket> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public BigDecimal totalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (products != null) {
            for (ItemInBasket i : products) {
                totalPrice = totalPrice.add(i.itemTotalValue());
            }
        }
        return totalPrice;
    }
}
