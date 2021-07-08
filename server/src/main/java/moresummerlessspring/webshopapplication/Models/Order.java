package moresummerlessspring.webshopapplication.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="ordercheckout")
public class Order {

    /*******************Variables*********************/
    @Id
    @GeneratedValue
    private int orderId;

    @ManyToOne//(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)//(fetch=FetchType.EAGER, optional=true,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId", nullable = false)//,nullable = true
    private User user;

    @NotEmpty
    @NotNull(message = "Country cannot be null")
    @Size(min=1,max=30,message="Country name should be at least 1 characters max 30 characters")
    private String country;

    @Digits(integer = 4,fraction = 0)
    @PositiveOrZero
    private int zipcode;

    @NotEmpty
    @NotNull(message = "City cannot be null")
    @Size(min=1,max=30,message="City name should be at least 1 characters max 30 characters")
    private String city;

    @NotEmpty
    @NotNull(message = "Address cannot be null")
    @Size(min=1,max=30,message="Address name should be at least 1 characters max 30 characters")
    private String address;


    @Digits(integer = 6,fraction = 0)
    @PositiveOrZero
    private int number;

    @Size(min=0,max=150,message="Description should be at least 0 characters max 150 characters")
    private String message;

    /*******************GETTERS AND SETTERS*********************/
    public void setUser(User user) { this.user = user; }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public User getUser() {
        return user;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
