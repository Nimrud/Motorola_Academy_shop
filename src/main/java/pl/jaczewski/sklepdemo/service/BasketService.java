package pl.jaczewski.sklepdemo.service;

import pl.jaczewski.sklepdemo.model.ItemInBasket;

import java.math.BigDecimal;
import java.util.List;

public interface BasketService {
    void addItem(ItemInBasket item);
    void removeItem(ItemInBasket item);
    ItemInBasket getItemByName(String name);
    int getQuantityReserved(String name);
    BigDecimal accumulatedPrice();
    BigDecimal finalPrice();
    List<ItemInBasket> getAllItems();
    BigDecimal getItemPrice(String name);
}
