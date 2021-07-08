package moresummerlessspring.webshopapplication.Repository;

import moresummerlessspring.webshopapplication.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer>  {
}
