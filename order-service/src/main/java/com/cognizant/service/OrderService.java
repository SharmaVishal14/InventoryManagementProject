package com.cognizant.service;


import com.cognizant.model.Order;
import java.util.List;
 
public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long orderId);
    List<Order> getAllOrders();
    Order updateOrderStatus(Long orderId, String status);
    List<Order> getOrdersByCustomerId(Long customerId);
}