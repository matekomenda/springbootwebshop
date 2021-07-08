package moresummerlessspring.webshopapplication.Repository;

import moresummerlessspring.webshopapplication.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}

