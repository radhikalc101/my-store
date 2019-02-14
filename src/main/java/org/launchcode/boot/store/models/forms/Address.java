package org.launchcode.boot.store.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@SequenceGenerator(name="addrSeq", initialValue=1, allocationSize=100)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addrSeq")
    private int id;

    @NotNull
    private String addressLine1;

    private String addressLine2;
    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private int zipcode;

    private String country = "United States";

    private Timestamp creationDateTime;

    private Timestamp updatedDateTime;

    // empty constructor
    public Address(){}

    // constructor
    public Address(String addressLine1,String addressLine2,String city,String state,int zipcode, Timestamp creationDateTime, Timestamp updatedDateTime){
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.creationDateTime = creationDateTime;
        this.updatedDateTime = updatedDateTime;
    }
    //getters
    public int getId(){ return id;}
    public String getAddressLine1(){ return addressLine1;}
    public String getAddressLine2(){ return addressLine2;}
    public String getCity(){ return city;}
    public String getState(){ return state;}
    public int getZipcode(){ return zipcode;}
    public String getCountry(){return country;}
    public Timestamp getCreationDateTime(){return creationDateTime;}
    public Timestamp getUpdatedDateTime(){ return updatedDateTime;}

    //setters

    public void setAddressLine1(String addressLine1){this.addressLine1 = addressLine1;}
    public void setAddressLine2(String addressLine2){ this.addressLine2 = addressLine2;}

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }


    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setUpdatedDateTime(Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("addressLine1:").append(addressLine1).append("addressLine2:").append(addressLine2)
                .append("city:").append(city).append("state:").append(state).append("zipcode:").append(zipcode)
                .append("country:").append(country).append("creationDateTime:").append(creationDateTime);

        return sb.toString();

    }
}
