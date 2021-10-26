package pl.jaczewski.sklepdemo.repository.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jaczewski.sklepdemo.model.ItemInBasket;

public interface BasketInterface extends JpaRepository<ItemInBasket, Long> {

    @Query("SELECT b FROM ItemInBasket b WHERE b.product.id = :id")
    ItemInBasket findProductById(@Param("id") long id);
}
