package pl.jaczewski.sklepdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private int quantityInStock;
    private int reserved;

    public enum Category {
        FOOD, DOMESTIC_DETERGENTS, ALCOHOL, TOYS, WEAPONS, HOUSEHOLD_GOODS, DRUGS
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
