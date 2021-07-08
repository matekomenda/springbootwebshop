package moresummerlessspring.webshopapplication.Service;

import moresummerlessspring.webshopapplication.Models.Cart;
import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Models.User;
import moresummerlessspring.webshopapplication.Models.Wishlist;
import moresummerlessspring.webshopapplication.Repository.CartRepository;
import moresummerlessspring.webshopapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public User register(User user){
        User newUser = new User();
        newUser = userRepository.findByEmail(user.getEmail());
        if (newUser == null) {
            Cart newCart = new Cart(0);
            user.setCart(newCart);
            Wishlist newWishlist = new Wishlist(0);
            user.setWishlist(newWishlist);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public User login(User user) {
        //User currentUser = new User();
        User currentUser = userRepository.findByEmail(user.getEmail());
        if(currentUser == null){
           return null;
        } else if(user.getPassword().equals(currentUser.getPassword())){
            return currentUser;
        } else {
            return null;
        }
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(int id) {
        User user = new User();
        user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }

        Cart usersCart = userRepository.findById(id).orElse(null).getCart();
        List<Product> newList = usersCart.getProductList();
        for(int i = 0; i < newList.size(); i++){
            newList.get(i).setCart(null);
            productService.updateProduct(newList.get(i).getId(),newList.get(i));
        }

        Wishlist usersWishList = userRepository.findById(id).orElse(null).getWishlist();
        List<Product> newList2 = usersWishList.getProductList();
        for(int i = 0; i < newList2.size(); i++){
            newList2.get(i).setWishlist(null);
            productService.updateProduct(newList2.get(i).getId(),newList2.get(i));
        }
        userRepository.deleteById(id);
        return "User with : " + id + " id has been deleted";
    }
}
