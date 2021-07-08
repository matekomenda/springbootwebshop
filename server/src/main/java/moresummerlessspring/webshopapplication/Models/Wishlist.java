package moresummerlessspring.webshopapplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Table(name="wishlist")
public class Wishlist {

    /*******************Variables*********************/
    @Id
    @GeneratedValue
    private int wishlistId;

    @JsonIgnore
    @OneToOne(mappedBy = "wishlist", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "wishlist")
    private List<Product> productList;

    @PositiveOrZero
    @Min(value = 0,message = "Total should be at least 0")
    private double total = 0;

    /*******************Constructors***************************/
    public Wishlist(double total){this.total = total;}
    public Wishlist(){}

    /*******************GETTERS AND SETTERS*********************/
    public int getWishlistId() { return wishlistId; }
    public List<Product> getProductList() { return productList; }
    public double getTotal() { return total; }
    public void setProductList(List<Product> cartItemList) { this.productList = cartItemList; }
    public void setTotal() {
        this.total=0;
        for(int i = 0; i < productList.size(); i++) {
            this.total += productList.get(i).getPrice();
        }
    }
    public void setWishlistId(int wishlistId) { this.wishlistId = wishlistId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }



}
