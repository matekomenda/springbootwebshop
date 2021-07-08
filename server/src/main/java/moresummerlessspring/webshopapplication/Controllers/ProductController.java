package moresummerlessspring.webshopapplication.Controllers;

import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Repository.CategoryRepository;
import moresummerlessspring.webshopapplication.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("productById/{id}")
    public ResponseEntity<Product> findItemById(@PathVariable int id){
        Logger.getAnonymousLogger().log(Level.INFO,"Find product by ID");
        Product newProduct = new Product();
        newProduct = service.getProductById(id);
        if(newProduct == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not Found items by ID");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
        }
    }
    @GetMapping("/productByName/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name){
        Logger.getAnonymousLogger().log(Level.INFO,"Find product by Name");
        return new ResponseEntity<Product>(service.getProductByName(name), HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        Logger.getAnonymousLogger().log(Level.INFO,"Get all products");
        return new ResponseEntity<List<Product>>(service.getProducts(), HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product){
        Logger.getAnonymousLogger().log(Level.INFO,"Update product by ID");
        Product newProduct = new Product();
        newProduct = service.updateProduct(id,product);
        if(newProduct == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to update product");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Product>(newProduct ,HttpStatus.OK);
        }
    }
    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        Logger.getAnonymousLogger().log(Level.INFO,"Delete product by ID");
        return new ResponseEntity<>(service.deleteProduct(id),HttpStatus.OK);
    }

    @PostMapping("/addProduct/{category_id}")
    public ResponseEntity<Product> addProduct(@PathVariable(value="category_id") int category_id,  @RequestBody Product product) throws ResourceNotFoundException {
        Logger.getAnonymousLogger().log(Level.INFO,"Add product");
        Product newProduct = new Product();
        newProduct = categoryRepository.findById(category_id).map(category -> {
            product.setCategory(category);
            return service.saveProduct(product);
        }).orElse(null);
        if (newProduct == null) {
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to add product");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Product>(newProduct,HttpStatus.OK);
        }
    }
}
