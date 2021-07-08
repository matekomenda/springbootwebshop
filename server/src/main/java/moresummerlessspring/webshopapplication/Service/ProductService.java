package moresummerlessspring.webshopapplication.Service;

import moresummerlessspring.webshopapplication.Models.Cart;
import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Models.Wishlist;
import moresummerlessspring.webshopapplication.Repository.CartRepository;
import moresummerlessspring.webshopapplication.Repository.ProductRepository;
import moresummerlessspring.webshopapplication.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public Product getProductById(int id){
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return repository.findByName(name);
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public String deleteProduct(int id){
        repository.deleteById(id);
        return "Product has been removed" + id;
    }

    public Product updateCart(Product product, int cartId) {
        Cart existingCart = cartRepository.findById(cartId).orElse(null);
        product.setCart(existingCart);
        return repository.save(product);
    }

    public Product updateProduct(int id,Product product) {
        Product existingProduct = repository.findById(id).orElse(null);
        if(existingProduct == null){
            return null;
        } else {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setImageFileName(product.getImageFileName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setCart(product.getCart());
            return repository.save(existingProduct);
        }
    }

    public Product updateWishList(Product product, int wishlistId) {
        Wishlist existingWishlist = wishlistRepository.findById(wishlistId).orElse(null);
        product.setWishlist(existingWishlist);
        return repository.save(product);
    }
}
