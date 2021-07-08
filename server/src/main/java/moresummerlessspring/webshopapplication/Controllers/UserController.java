package moresummerlessspring.webshopapplication.Controllers;

import moresummerlessspring.webshopapplication.Models.Order;
import moresummerlessspring.webshopapplication.Models.User;
import moresummerlessspring.webshopapplication.Repository.UserRepository;
import moresummerlessspring.webshopapplication.Service.OrderService;
import moresummerlessspring.webshopapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user)  {
        Logger.getAnonymousLogger().log(Level.INFO,"Register user");
        User newUser = new User();
        newUser = userService.register(user);
        if(newUser== null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not Found user by ID");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(userService.register(user),HttpStatus.OK);
        }
    }

    @PostMapping("/mylogin")
    public ResponseEntity<User> login(@RequestBody User user) {
        Logger.getAnonymousLogger().log(Level.INFO,"Login user");
        User newUser = new User();
        newUser = userService.login(user);
        if(newUser == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to login");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(userService.login(user),HttpStatus.OK);
        }
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        Logger.getAnonymousLogger().log(Level.INFO,"Find user by ID");
        User newUser = new User();
        newUser = userService.getUser(id);
        if(newUser == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to get user by ID");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(newUser,HttpStatus.OK);
        }
    }

    @PostMapping("/createOrder/{userid}")
    public  ResponseEntity<Order> createOrder(@PathVariable int userid,@RequestBody Order order) throws Exception{
        Logger.getAnonymousLogger().log(Level.INFO,"Create order for user");
        User currentUser = new User();
        currentUser = userRepository.findById(userid).orElse(null);
        if(currentUser == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to create order");
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            order.setUser(currentUser);
            return new ResponseEntity<Order>(orderService.saveOrder(order),HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){

        Logger.getAnonymousLogger().log(Level.INFO,"Delete user by ID");
        String response = userService.deleteUser(id);
        if(response == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to delete user by ID");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

}
