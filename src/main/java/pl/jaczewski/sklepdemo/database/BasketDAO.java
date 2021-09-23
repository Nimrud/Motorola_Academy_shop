package pl.jaczewski.sklepdemo.database;

import lombok.Getter;
import lombok.Setter;
import pl.jaczewski.sklepdemo.model.ItemInBasket;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class BasketDAO {

    private List<ItemInBasket> products = new LinkedList<>();

    private BigDecimal promotion1 = new BigDecimal("0.95");
    private BigDecimal promotion2 = new BigDecimal("0.9");

    public List<ItemInBasket> getItems() {
        return Collections.unmodifiableList(products);
    }

    public BigDecimal accumulatedPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (products != null) {
            for (ItemInBasket i : products) {
                totalPrice = totalPrice.add(i.itemTotalValue());
            }
        }
        return totalPrice;
    }

    public BigDecimal totalPrice() {
        // TODO
        // cena z promocjami (zwraca najkorzystniejszą dla klienta opcję)
        return new BigDecimal("-1");
    }

    public ItemInBasket getItemByName(String name) {
        if (products != null) {
            for(ItemInBasket i : products) {
                if (name.equals(i.getProduct().getName())) {
                    return i;
                }
            }
        }
        return null;
    }

    public void addItem(ItemInBasket item) {
        // TODO
    }
}
