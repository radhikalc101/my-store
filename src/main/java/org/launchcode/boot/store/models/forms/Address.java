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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double latitude;
    private double longitude;

    // empty constructor
    public Address(){}

    // constructor
    public Address(String addressLine1,String addressLine2,String city,String state,int zipcode, Timestamp creationDateTime, Timestamp updatedDateTime,double latitude, double longitude){
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.creationDateTime = creationDateTime;
        this.updatedDateTime = updatedDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
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
        sb.append("addressLine1: ").append(addressLine1).append("\n").append("addressLine2:").append(addressLine2).append("\n")
                .append("city:").append(city).append("\n").append("state:").append(state).append("\n").append("zipcode:").append(zipcode).append("\n")
                .append("country:").append(country).append("\n").append("creationDateTime:").append(creationDateTime).append("\n").append("latitude").append(latitude).append("\n")
                .append("longitude").append(longitude);

        return sb.toString();

    }
}
