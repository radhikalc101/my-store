package org.launchcode.boot.store.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name="catSeq", initialValue=1, allocationSize=100)
public class Category {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catSeq")
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
    public void setId(int id){
        this.id = id;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
