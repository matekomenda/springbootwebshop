package moresummerlessspring.webshopapplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="category")
public class Category {

    /*******************Variables*********************/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    @NotEmpty
    @NotNull(message = "Name cannot be null")
    @Size(min=1,max=30,message="Category name should be at least 1 characters max 30 characters")
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)//,targetEntity = Product.class,fetch = FetchType.EAGER,orphanRemoval = true)//,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

    /*******************Constructors***************************/
    public Category(){ }

    /*******************GETTERS AND SETTERS*********************/
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getCategoryId() { return categoryId; }
    public List<Product> getProducts() { return products; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setProducts(List<Product> products) { this.products = products; }
}
