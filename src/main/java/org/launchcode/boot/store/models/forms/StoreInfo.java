package org.launchcode.boot.store.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity //This tells Hibernate to make a table out of this class
public class StoreInfo {
    // fields

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String storeLicense;

    private Timestamp creationDateTime;

    private Timestamp updatedDateTime;

    @OneToOne
    @JoinColumn(name = "Address_id")
    private Address storeAddress;

    @OneToOne
    @JoinColumn(name = "OwnerAccountInfo_id")
    private OwnerAccountInfo ownerAccountInfo;

    //constructors
    public StoreInfo(){}

    public StoreInfo(String name, String storeLicense, Timestamp creationDateTime, Timestamp updatedDateTime, Address storeAddress, OwnerAccountInfo ownerAccountInfo){
        this.name = name;
        this.storeLicense = storeLicense;
        this.creationDateTime = creationDateTime;
        this.updatedDateTime = updatedDateTime;
        this.storeAddress = storeAddress;
        this.ownerAccountInfo = ownerAccountInfo;
    }
    // getters
    public int getId(){ return id;}
    public String getName(){ return name;}
    public String getStoreLicense(){ return storeLicense;}
    public Timestamp getCreationDateTime(){ return creationDateTime;}
    public Timestamp getUpdatedDateTime(){ return updatedDateTime;}

    public Address getStoreAddress() {
        return storeAddress;
    }

    public OwnerAccountInfo getOwnerAccountInfo() {
        return ownerAccountInfo;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setStoreLicense(String storeLicense){
        this.storeLicense = storeLicense;
    }
    public void setCreationDateTime(Timestamp creationDateTime){
        this.creationDateTime = creationDateTime;
    }
    public void setUpdatedDateTime(Timestamp updatedDateTime){
        this.updatedDateTime = updatedDateTime;
    }
    public void setStoreAddress(Address storeAddress){
        this.storeAddress = storeAddress;
    }
    public void setOwnerAccountInfo(OwnerAccountInfo ownerAccountInfo){
        this.ownerAccountInfo = ownerAccountInfo;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("name:").append(name).append("storeLicense:").append(storeLicense).append("creationDateTime:").append(creationDateTime)
            .append("storeAddress:").append(storeAddress).append("ownerAccountInfo:").append(ownerAccountInfo);
        return sb.toString();

    }
}

