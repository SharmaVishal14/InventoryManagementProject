package com.cts.inventorymanagement.order.service;

import java.util.List;
import java.util.Map;

import com.cts.inventorymanagement.order.model.Order;
import com.cts.inventorymanagement.order.model.OrderDto;

public interface OrderService {
    List<OrderDto> getAllOrders();
    List<OrderDto> getOrdersOfProduct(Long productId);
    List<OrderDto> getOrdersOfCustomer(Long customerId);
    OrderDto createOrder(OrderDto orderDto);
    Map<String, String> updateOrderStatus(Long orderId, Order.Status orderStatus);
}