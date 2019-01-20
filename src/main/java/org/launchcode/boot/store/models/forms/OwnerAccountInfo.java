package org.launchcode.boot.store.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
public class OwnerAccountInfo {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String firstName;

    private String middleName;
    @NotNull
    private String lastName;

    @NotNull
    @Size(min=1,max=20)
    private String email;
    @NotNull
    @Size(min=1,max=20, message = "Please enter the valid password")
    private String password;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String drivingLisence;
    @NotNull
    private String gender;

    private Timestamp creationDateTime;

    private Timestamp updatedDateTime;

    private Timestamp expirationDate;

    @OneToOne
    @JoinColumn(name = "Address_id")
    private Address ownerAddress;

    //Do not do this
//    @OneToMany
//    @JoinColumn(name = "StoreInfo_id")
//    private List<StoreInfo> stores;

    // constructors empty and with fields
    public OwnerAccountInfo(){}

    public OwnerAccountInfo(String firstName, String middleName, String lastName, String email,
                            String password,  String dateOfBirth, String phoneNumber, String drivingLisence,
                            Timestamp updatedDateTime, Timestamp expirationDate, Address ownerAddress, String gender){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.drivingLisence = drivingLisence;
        this.updatedDateTime = updatedDateTime;
        this.expirationDate = expirationDate;
        this.ownerAddress = ownerAddress;
        this.gender = gender;

    }
    // getters


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDrivingLisence() {
        return drivingLisence;
    }
    public String getGender(){
        return gender;
    }

    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }

    public Timestamp getUpdatedDateTime() {
        return updatedDateTime;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    // setters


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDrivingLisence(String drivingLisence) {
        this.drivingLisence = drivingLisence;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setUpdatedDateTime(Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Address getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(Address ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    @Override
    public String toString(){// this toString method will print actual values to the console
        StringBuilder sb = new StringBuilder();

        sb.append("firstName:").append(firstName).append("\n")
                .append("lastname:").append(lastName).append("email:").append(email).append("password:")
                .append(password).append("phoneNumber:").append(phoneNumber).append("gender:").append(gender)
                .append("dateOfBirth:").append(dateOfBirth).append("creationDateTime:").append(creationDateTime);
        return sb.toString();
    }
}
