package pl.jaczewski.sklepdemo.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.jaczewski.sklepdemo.database.BasketDAO;
import pl.jaczewski.sklepdemo.model.ItemInBasket;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketDAO basketDAO = new BasketDAO();

    @Override
    public void addItem(@NonNull ItemInBasket item) {
        basketDAO.addItem(item);
    }

    @Override
    public void removeItem(@NonNull ItemInBasket item) {
        basketDAO.removeItem(item);
    }

    @Override
    public ItemInBasket getItemByName(String name) {
        return basketDAO.getItemByName(name);
    }

    @Override
    public int getQuantityReserved(String name) {
        return basketDAO.getQuantityReserved(name);
    }

    @Override
    public BigDecimal getItemPrice(String name) {
        BigDecimal productPrice = getItemByName(name).getProduct().getPrice();
        int quantityReserved = getQuantityReserved(name);
        return new BigDecimal(quantityReserved).multiply(productPrice);
    }

    @Override
    public BigDecimal accumulatedPrice() {
        return basketDAO.accumulatedPrice();
    }

    @Override
    public BigDecimal finalPrice() {
        return basketDAO.finalPrice();
    }

    public List<ItemInBasket> getAllItems() {
        return basketDAO.getAllItems();
    }
}
