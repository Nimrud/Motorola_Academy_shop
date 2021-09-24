package pl.jaczewski.sklepdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemInBasket {

    // fields
    private Product product;
    private int quantityInBasket;

    // constructors
    public ItemInBasket(Product product, int quantity) {
        this.product = product;
        this.quantityInBasket = quantity;
    }

    public ItemInBasket() {
    }

    // public methods
    public BigDecimal itemTotalValue() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantityInBasket));
    }
}
