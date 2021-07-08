package moresummerlessspring.webshopapplication.Controllers;

import moresummerlessspring.webshopapplication.Models.Category;
import moresummerlessspring.webshopapplication.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id){
        Logger.getAnonymousLogger().log(Level.INFO,"Get Category by ID");
        Category newCategory = new Category();
        newCategory = service.getCategoryById(id);
        if(newCategory == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not found category by id");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Category>(service.getCategoryById(id),HttpStatus.OK);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        Logger.getAnonymousLogger().log(Level.INFO,"Get Categories");
        return new ResponseEntity<List<Category>>(service.getCategories(), HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Logger.getAnonymousLogger().log(Level.INFO,"Add Category");
        return new ResponseEntity<Category>(service.saveCategory(category),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        Logger.getAnonymousLogger().log(Level.INFO,"Delete Category by ID");
        return new ResponseEntity<>(service.deleteCategory(id),HttpStatus.OK);
    }

    @PutMapping("updateCategory/{id}")
    public  ResponseEntity<Category> updateCategory(@PathVariable int id,@RequestBody Category category){
        Logger.getAnonymousLogger().log(Level.INFO,"Update Category by ID");
        Category newCategory = new Category();
        newCategory = service.updateCategory(id,category);
        if(newCategory ==  null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Not able to update category by ID");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.updateCategory(id,category),HttpStatus.OK);
        }
    }
}
