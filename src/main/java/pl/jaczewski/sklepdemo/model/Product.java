package pl.jaczewski.sklepdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Nazwa produktu nie może być pusta")
    @Size(min = 3, message = "Nazwa produktu musi składać się z przynajmniej 3 znaków")
    private String name;
    private String description;
    @NotBlank
    @DecimalMin(value = "0.01", message = "Cena nie może być niższa niż 1 gr.")
    private BigDecimal price;
    private Category category;
    private int quantityInStock;
    private int reserved;

    public enum Category {
        FOOD("art. spożywcze"),
        DOMESTIC_DETERGENTS("detergenty"),
        ALCOHOL("alkohole"),
        TOYS("zabawki"),
        WEAPONS("broń"),
        HOUSEHOLD_GOODS("art. gospodarstwa domowego"),
        DRUGS("narkotyki"),
        NONE("brak");

        private final String displayValue;

        private Category(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }

    public Product(String name, String description, BigDecimal price, Category category, int quantityInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantityInStock = quantityInStock;
        reserved = 0;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(0)) > 0) {
            this.price = price;
        }
     }

     public int addReserved(int quantity) {
        if (quantity <= quantityInStock) {
            this.reserved += quantity;
            return quantity;
        }
        return 0;
     }

     public int unReserve(int quantity) {
        if (quantity <= reserved) {
            reserved -= quantity;
            return quantity;
        }
        return 0;
     }

     public int adjustStock(int quantity) {
        int newQuantity = quantityInStock + quantity;
        if (newQuantity >= 0) {
            return quantityInStock = newQuantity;
        } else {
            return 0;
        }
     }

     public int finalisePurchase(int quantity) {
        if (quantity <= reserved) {
            quantityInStock -= quantity;
            reserved -= quantity;
            return quantity;
        }
        return 0;
     }

     public String getAvailability() {
        if (quantityInStock > 10) {
            return "duża ilość";
        } else if(quantityInStock > 3) {
            return "średnia ilość";
        } else {
            return "ostatnie sztuki";
        }
     }
}
