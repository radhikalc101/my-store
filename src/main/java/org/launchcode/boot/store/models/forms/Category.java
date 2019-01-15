package org.launchcode.boot.store.models.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {

    @GeneratedValue
    @Id
    private int id;

    @NotNull
    @Size(min=1,max=20)
    private String name;

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
}
