package moresummerlessspring.webshopapplication.Service;

import moresummerlessspring.webshopapplication.Models.Order;
import moresummerlessspring.webshopapplication.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) throws Exception{ return orderRepository.save(order); }
}
