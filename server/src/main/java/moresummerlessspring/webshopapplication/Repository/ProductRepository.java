package moresummerlessspring.webshopapplication.Repository;

import moresummerlessspring.webshopapplication.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>  {

    Product findByName(String name);

    @Query(value = "SELECT * FROM Product p WHERE p.cart_id = :cartId",
    nativeQuery = true)
    List<Product> selectProductsByCart(@Param("cartId") int cartId);

    @Query(value = "SELECT * FROM Product p WHERE p.wishlist_id = :wishlistId",
    nativeQuery = true)
    List<Product> selectProductsByWishList(int wishlistId);

}
