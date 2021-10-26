//package pl.jaczewski.sklepdemo.model;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.web.context.annotation.SessionScope;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Data
//@SessionScope
//@NoArgsConstructor
//public class Basket {
//
//    // fields
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @NotNull
//    @ManyToOne
//    private Product product;
//    @NotBlank
//    private int quantityInBasket;
//
//    // constructors
//    public Basket(Product product, int quantityInBasket) {
//        this.product = product;
//        this.quantityInBasket = quantityInBasket;
//    }
//}
