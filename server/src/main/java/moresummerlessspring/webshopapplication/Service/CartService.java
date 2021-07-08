package moresummerlessspring.webshopapplication.Service;

import moresummerlessspring.webshopapplication.Models.Cart;
import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Repository.CartRepository;
import moresummerlessspring.webshopapplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    public Cart saveCart(Cart cart) {
        return repository.save(cart);
    }

    public Cart getCartById(int id) {
        Cart newCart = new Cart ();
        newCart = repository.findById(id).orElse(null);
        if(newCart == null){
            return null;
        } else {
            return newCart;
        }
    }

    public List<Product> getCartItems(int cartId) {
        Cart repo = new Cart();
        repo = repository.findById(cartId).orElse(null);

        if(repo == null){
            return null;
        } else {
            return repo.getProductList();
        }

    }

    public Cart updateCart(int cartId,Product product) {
        Cart existingCart = repository.findById(cartId).orElse(null);
        if(existingCart == null){
            return null;
        }
        List<Product> productsInCart = productRepository.selectProductsByCart(existingCart.getCartId());
        existingCart.setProductList(productsInCart);
        existingCart.setTotal();
        return repository.save(existingCart);
    }

    public String deleteProductFromCart(int cartId, int productId) {
        Cart existingCart = new Cart();
        List<Product> newList = new ArrayList<>();
        Product product = new Product();
        existingCart = repository.findById(cartId).orElse(null);
        if(existingCart == null){
            return null;
        }
        newList = existingCart.getProductList();
        product = productRepository.findById(productId).orElse(null);
        if(product == null){
            return null;
        }
        for(int i = 0; i < newList.size(); i++){
            if(newList.get(i) == product){
                newList.remove(i);
            }
        }
        product.setCart(null);
        productService.updateProduct(productId,product);
        productRepository.save(product);
        existingCart.setProductList(newList);
        existingCart.setTotal();
        repository.save(existingCart);
        return "Successfully deleted";
    }

}
