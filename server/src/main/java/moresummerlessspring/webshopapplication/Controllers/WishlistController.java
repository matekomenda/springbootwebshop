package moresummerlessspring.webshopapplication.Controllers;

import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Models.Wishlist;
import moresummerlessspring.webshopapplication.Service.ProductService;
import moresummerlessspring.webshopapplication.Service.WishlistService;
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
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductService productService;

    @GetMapping("/wishlist/{id}")
    public ResponseEntity<Wishlist> findWishById(@PathVariable int id){
        Logger.getAnonymousLogger().log(Level.INFO,"Find wishlist by ID");
        Wishlist newWishlist = new Wishlist();
        newWishlist =  wishlistService.getWishListById(id);
        if(newWishlist == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not Found wishlist by ID");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Wishlist>(newWishlist,HttpStatus.OK);
        }
    }

    @GetMapping("/wishlist/products/{wishlistId}")
    public ResponseEntity<List<Product>> getWishListItems(@PathVariable int wishlistId) {
        Logger.getAnonymousLogger().log(Level.INFO,"Get wishlist by id");
        List<Product> newList = new ArrayList<>();
        newList = wishlistService.getWishListItems(wishlistId);
        if(newList == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not Found wishlist items");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Product>>(newList,HttpStatus.OK);
        }
    }

    @PostMapping("/addToWishList/{wishlistId}/{productId}")
    public ResponseEntity<Product> addProductToWishList(@PathVariable int wishlistId, @PathVariable int productId) {
        Logger.getAnonymousLogger().log(Level.INFO,"Add product to wishlist by ID");
        Product newProduct = new Product();
        Wishlist newWishlist = new Wishlist();
        newProduct = productService.updateWishList(productService.getProductById(productId), wishlistId);
        newWishlist = wishlistService.updateWishList(wishlistService.getWishListById(wishlistId),productService.getProductById(productId));
        if(newProduct == null || newWishlist == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to add item to wishlist");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Product>(newProduct,HttpStatus.OK);
        }
    }

    @DeleteMapping("/wishlist/{wishlistId}/{productId}")
    public ResponseEntity<Wishlist> deleteProductFromWishList(@PathVariable int wishlistId, @PathVariable int productId) {
        Logger.getAnonymousLogger().log(Level.INFO,"Delete product from wishlist by ID");
        Wishlist newWishList = new Wishlist();
        newWishList = wishlistService.deleteProductFromWishlist(wishlistId, productId);
        if(newWishList == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to delete from wishlist by ID");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Wishlist>(newWishList,HttpStatus.OK);
        }
    }

}
