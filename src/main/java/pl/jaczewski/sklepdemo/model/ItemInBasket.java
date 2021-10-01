package pl.jaczewski.sklepdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Component
@Entity
@NoArgsConstructor
public class ItemInBasket {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @ManyToOne
    private Product product;
    private int quantityInBasket;

    // constructors
    public ItemInBasket(Product product, int quantity) {
        this.product = product;
        this.quantityInBasket = quantity;
    }

    // public methods
    public BigDecimal itemTotalValue() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantityInBasket));
    }
}
