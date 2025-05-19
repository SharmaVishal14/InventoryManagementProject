package com.cognizant.service.impl;

import com.cognizant.model.Order;
import com.cognizant.model.OrderItem;
import com.cognizant.exception.OrderNotFoundException;
import com.cognizant.repository.OrderRepository;
import com.cognizant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order orderRequest) {
        // Validate that the order has items
        if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
     
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setOrderDate(LocalDate.now());
        order.setStatus("Pending");
     
        for (OrderItem itemRequest : orderRequest.getItems()) {
            if (itemRequest.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            if (itemRequest.getProductId() == null) {
                throw new IllegalArgumentException("Product ID must be specified");
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemRequest.getProductId());
            orderItem.setQuantity(itemRequest.getQuantity());
            order.addItem(orderItem);
        }
     
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}