package pl.jaczewski.sklepdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
public class Product {
    private int id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    @DecimalMin("0.01")
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
}
