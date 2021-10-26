package pl.jaczewski.sklepdemo.service.database;

import lombok.NonNull;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.jaczewski.sklepdemo.model.ItemInBasket;
import pl.jaczewski.sklepdemo.repository.database.BasketDaoDB;
import pl.jaczewski.sklepdemo.service.BasketService;

import java.math.BigDecimal;
import java.util.List;

//@Scope("session")
@Service
@Primary
public class BasketServiceDbImpl implements BasketService {

    private final BasketDaoDB basketDaoDB;

    public BasketServiceDbImpl(BasketDaoDB basketDaoDB) {
        this.basketDaoDB = basketDaoDB;
    }

    @Override
    public void addItem(@NonNull ItemInBasket item) {
        basketDaoDB.addItem(item);
    }

    @Override
    public void removeItem(@NonNull ItemInBasket item) {
        basketDaoDB.removeItem(item);
    }

    @Override
    public ItemInBasket getItemByName(String name) {
        return basketDaoDB.getItemByName(name);
    }

    @Override
    public int getQuantityReserved(String name) {
        return basketDaoDB.getQuantityReserved(name);
    }

    @Override
    public BigDecimal getItemPrice(String name) {
        BigDecimal productPrice = getItemByName(name).getProduct().getPrice();
        int quantityReserved = getQuantityReserved(name);
        return new BigDecimal(quantityReserved).multiply(productPrice);
    }

    @Override
    public BigDecimal accumulatedPrice() {
        return basketDaoDB.accumulatedPrice();
    }

    @Override
    public BigDecimal finalPrice() {
        return basketDaoDB.finalPrice();
    }

    public List<ItemInBasket> getAllItems() {
        return basketDaoDB.getAllItems();
    }
}
