package pl.jaczewski.sklepdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemInBasket {

    private Product product;
    private int quantity;

    public ItemInBasket(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal itemTotalValue() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}