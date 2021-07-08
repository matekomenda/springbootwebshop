package moresummerlessspring.webshopapplication.Controllers;

import moresummerlessspring.webshopapplication.Models.Cart;
import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Service.CartService;
import moresummerlessspring.webshopapplication.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/addCart")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Logger.getAnonymousLogger().log(Level.INFO,"Add Cart");

        return new ResponseEntity<Cart>(cartService.saveCart(cart),HttpStatus.OK);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> findCartById(@PathVariable int id){
        Logger.getAnonymousLogger().log(Level.INFO,"Find Cart by ID");
        Cart newCart = new Cart();
        newCart = cartService.getCartById(id);
        if(newCart == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not found cart by ID");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Cart>(newCart,HttpStatus.OK);
        }
    }

    @GetMapping("/cart/products/{cartId}")
    public ResponseEntity<List<Product>> getCartItems(@PathVariable int cartId) {
        Logger.getAnonymousLogger().log(Level.INFO,"Get Cart items");
        List<Product> newList = new ArrayList<>();
        newList =  cartService.getCartItems(cartId);
        if(newList == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not found cart items");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity< List<Product>>(newList,HttpStatus.OK);
        }
    }

    @DeleteMapping("/cart/{cartId}/{productId}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable int cartId, @PathVariable int productId) {
        Logger.getAnonymousLogger().log(Level.INFO,"Delete product from Cart");
        if(cartService.deleteProductFromCart(cartId, productId) == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to delete from product");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
        }
    }

    @PostMapping("/addToCart/{cartId}/{productId}")
    public ResponseEntity<Product> addProductToCart(@PathVariable int cartId, @PathVariable int productId) {
        Logger.getAnonymousLogger().log(Level.INFO,"Add product to Cart");
        if(productService.updateCart(productService.getProductById(productId), cartId) != null && cartService.updateCart(cartId, productService.getProductById(productId)) != null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to add product to cart");
            return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}
