package moresummerlessspring.webshopapplication.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="product")
public class Product {

    /*******************Variables*********************/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @NotEmpty
    @NotNull(message = "Name cannot be null")
    @Size(min=1,max=30,message="Product name should be at least 1 characters max 30 characters")
    private String name;

    @PositiveOrZero
    @Min(value=0,message="Price should be at least 0.1$")
    private double price;

    @PositiveOrZero
    @Min(value=0,message = "Quantity's minimum value should be 0")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)//,nullable = true
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wishlistId")
    private Wishlist wishlist;

    @Size(min=0,max=150,message = "imageFileName should be at least 1 characters max 150 characters")
    private String imageFileName;

    @Size(min=0,max=150,message = "Description should be at least 1 characters max 150 characters")
    private String description;

    /*******************Constructors***************************/
    public Product(){}
    public Product(int id,String name,double price, int quantity,String imageFileName,String description){
        this.productId = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageFileName = imageFileName;
        this.description = description;
    }

    /*******************GETTERS AND SETTERS*********************/
    public int getId(){
        return productId;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){ return quantity; }
    public String getImageFileName(){
        return imageFileName;
    }
    public String getDescription(){
        return description;
    }
    public Cart getCart() { return cart; }
    public int getProductId() { return productId; }
    public Category getCategory() { return category; }
    public Wishlist getWishlist() { return wishlist; }
    public void setId(int id){
        this.productId = id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setProductId(int productId) { this.productId = productId; }
    public void setPrice(double price){
        this.price=price;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public void setCategory(Category category){
        this.category=category;
    }
    public void setImageFileName(String imageFileName){
        this.imageFileName=imageFileName;
    }
    public void setWishlist(Wishlist wishlist) { this.wishlist = wishlist; }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCart(Cart cart) { this.cart = cart;}
}
