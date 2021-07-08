package moresummerlessspring.webshopapplication.Service;

import moresummerlessspring.webshopapplication.Models.Category;
import moresummerlessspring.webshopapplication.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getCategories() { return repository.findAll(); }

    public  Category saveCategory(Category category) {
        return repository.save(category);
    }

    public String deleteCategory(int id) {
       repository.deleteById(id);
       return "Category " + id +"with id has been deleted";
    }

    public Category updateCategory(int id, Category category) {
        Category existingCategory = new Category();
        existingCategory = repository.findById(id).orElse(null);
        if(existingCategory == null){
           return null;
        } else {
            existingCategory.setName(category.getName());
            return repository.save(existingCategory);
        }
    }

    public Category getCategoryById(int id) {
        return repository.findById(id).orElse(null);
    }

}
