package pl.jaczewski.sklepdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Component
@SessionScope
@NoArgsConstructor
public class ItemInBasket {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @ManyToOne
    private Product product;
    @NotBlank
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
