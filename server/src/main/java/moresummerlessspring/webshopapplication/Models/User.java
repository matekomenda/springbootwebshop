package moresummerlessspring.webshopapplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    /*******************Variables*********************/
    @Id
    @GeneratedValue
    private int userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wishlistId")
    private Wishlist wishlist;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Order> orders;

    @NotEmpty
    @NotNull(message = "Name cannot be null")
    @Size(min=1,max=30,message="Product name should be at least 1 characters max 30 characters")
    public String name;

    @Column(unique=true)
    @NotNull(message = "Email cannot be null")
    public String email;

    @NotEmpty
    @NotNull(message = "Password cannot be null")
    @Size(min=6,max=30,message="Password  should be at least 6 characters max 30 characters")
    public String password;

    /*******************Constructors***************************/
    public User(){}

    /*******************GETTERS AND SETTERS*********************/
    public Cart getCart() {
        return cart;
    }
    public Wishlist getWishlist(){return wishlist; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getUserId() { return userId; }
    public List<Order> getOrders() { return orders; }
    public void setCart(Cart cart) { this.cart = cart; }
    public void setWishlist(Wishlist wishlist){ this.wishlist = wishlist; }
    public void setName(String name) { this.name = name; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setUserId(int userId) { this.userId = userId; }


}
