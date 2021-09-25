package pl.jaczewski.sklepdemo.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import pl.jaczewski.sklepdemo.model.ItemInBasket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class BasketDAO {

    // fields
    private List<ItemInBasket> products = new ArrayList<>();
    private BigDecimal promotion1 = new BigDecimal("0.95");
    private BigDecimal promotion2 = new BigDecimal("0.9");

    // constructor
    public BasketDAO() {
    }

    // public methods
    public List<ItemInBasket> getAllItems() {
        return products;
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

    public BigDecimal finalPrice() {
        // TODO
        // cena z promocjami (zwraca najkorzystniejszą dla klienta opcję)
        BigDecimal finalPrice = accumulatedPrice();
        if (accumulatedPrice().compareTo(new BigDecimal("500")) > 0) {
            return accumulatedPrice().multiply(promotion2).setScale(2, RoundingMode.HALF_UP);
        } else if (accumulatedPrice().compareTo(new BigDecimal("200")) > 0) {
            return accumulatedPrice().multiply(promotion1).setScale(2, RoundingMode.HALF_UP);
        }
        return finalPrice;
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

    public int getQuantityReserved(String name) {
        if (products != null) {
            for(ItemInBasket i : products) {
                if (name.equals(i.getProduct().getName())) {
                    return i.getQuantityInBasket();
                }
            }
        }
        return 0;
    }

    public int addItem(ItemInBasket item) {
        if (item.getQuantityInBasket() <= item.getProduct().getQuantityInStock()) {
            item.getProduct().setReserved(item.getProduct().getReserved() + item.getQuantityInBasket());
            //item.getProduct().addReserved(item.getQuantityInBasket());
            products.add(item);
            return item.getQuantityInBasket();
        } else {
            // TODO
            System.out.println("Nie można dodać do koszyka: zamawiana ilość jest większa niż stan magazynowy");
            return 0;
        }
    }

    public void removeItem(ItemInBasket item) {
        if (item.getQuantityInBasket() <= item.getProduct().getReserved()) {
            item.getProduct().setReserved(0);
            products.remove(item);
        } else {
            // TODO
            System.out.println("Błąd przy usuwaniu produktu z koszyka");
        }
    }
}
