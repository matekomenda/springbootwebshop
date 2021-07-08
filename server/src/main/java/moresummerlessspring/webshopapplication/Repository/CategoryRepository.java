package moresummerlessspring.webshopapplication.Repository;

import moresummerlessspring.webshopapplication.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByName(String name);
}
