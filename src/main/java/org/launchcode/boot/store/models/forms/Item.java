package org.launchcode.boot.store.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private int quantity;

    @NotNull
    private float price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

// add bellow to constructor and get,set methods
//    @NotNull
//    private int aisle;
//
//    @NotNull
//    private DateFormat expirationDate;
// published field
// upload img


    public Item(){}
    public Item(String name, String description, int quantity, float price, Category category, Brand brand){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}
