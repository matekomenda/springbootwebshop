package moresummerlessspring.webshopapplication.Service;

import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Models.Wishlist;
import moresummerlessspring.webshopapplication.Repository.ProductRepository;
import moresummerlessspring.webshopapplication.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    public Wishlist getWishListById(int wishlistId) {
        return wishlistRepository.findById(wishlistId).orElse(null);
    }

    public Wishlist updateWishList(Wishlist wishList, Product productById) {
        Wishlist existingWishList = wishlistRepository.findById(wishList.getWishlistId()).orElse(null);
        if(existingWishList== null){
           return null;
        }
        List<Product> productsInWishList = productRepository.selectProductsByWishList(existingWishList.getWishlistId());
        existingWishList.setProductList(productsInWishList);
        existingWishList.setTotal();
        return wishlistRepository.save(existingWishList);
    }

    public Wishlist deleteProductFromWishlist(int wishlistId, int productId) {
        Wishlist existingWishlist = new Wishlist();
        List<Product> newList = new ArrayList<>();
        Product product = new Product();

        existingWishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if(existingWishlist == null){
            return null;
        }
        newList = existingWishlist.getProductList();
        product = productRepository.findById(productId).orElse(null);
        if(product == null){
            return null;
        }
        for(int i = 0; i < newList.size(); i++){
            if(newList.get(i) == product){
                newList.remove(i);
            }
        }
        product.setWishlist(null);
        productService.updateProduct(productId,product);
        productRepository.save(product);
        existingWishlist.setProductList(newList);

        existingWishlist.setTotal();
        return wishlistRepository.save(existingWishlist);

    }

    public List<Product> getWishListItems(int wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        return wishlist.getProductList();
    }
}
