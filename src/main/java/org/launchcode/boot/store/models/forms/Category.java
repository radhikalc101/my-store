package org.launchcode.boot.store.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @GeneratedValue
    @Id
    private int id;

    @NotNull
    @Size(min=1,max=20)
    private String name;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Item> items = new ArrayList<>();

    // default and with field constructor
    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
