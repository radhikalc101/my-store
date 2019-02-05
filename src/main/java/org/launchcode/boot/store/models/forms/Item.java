package org.launchcode.boot.store.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private boolean isPublished;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @NotNull
    private int aisle;

    @ManyToOne
    private StoreInfo storeInfo;

    @NotNull
    private String expirationDate;

    @OneToOne
    private DBFile image;

    public Item(){}
    public Item(String name, String description, int quantity, float price, Category category, Brand brand,
                int aisle,String expirationDate, StoreInfo storeInfo, DBFile image){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.brand = brand;
        this.aisle = aisle;
        this.expirationDate = expirationDate;
        this.isPublished = false;
        this.storeInfo = storeInfo;
        this.image = image;
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

    public int getAisle() {
        return aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public StoreInfo getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }

    public DBFile getImage() {
        return image;
    }

    public void setImage(DBFile image) {
        this.image = image;
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
                ", isPublished=" + isPublished +
                ", store=" + storeInfo.getName() +
                ", imageId=" + image.getId() +
                '}';
    }
}
