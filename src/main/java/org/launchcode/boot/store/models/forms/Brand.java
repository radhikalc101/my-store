package org.launchcode.boot.store.models.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Brand {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    public Brand(){}
    public Brand(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
