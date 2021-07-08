package moresummerlessspring.webshopapplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Table(name="cart")
public class Cart {

    /*******************Variables*********************/
    @Id
    @GeneratedValue
    private int cartId;

    @JsonIgnore
    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    private User user;


    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<Product> productList;

    @PositiveOrZero
    @Min(value = 0,message = "Total should be at least 0")
    private double total = 0;

    /*******************Constructors***************************/
    public Cart(double total){ this.total = total; }
    public Cart(){ }

    /*******************GETTERS AND SETTERS*********************/
    public int getCartId() { return cartId; }
    public List<Product> getProductList() { return productList; }
    public User getUser() { return user; }
    public double getTotal() { return total; }
    public void setUser(User user) { this.user = user; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public void setProductList(List<Product> cartItemList) { this.productList = cartItemList; }
    public void setTotal() {
        this.total=0;
        for(int i = 0; i < productList.size(); i++) {
            this.total += productList.get(i).getPrice();
        }
    }

}
