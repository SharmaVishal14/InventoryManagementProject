package com.cts.inventorymanagement.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cts.inventorymanagement.order.model.Order;
import com.cts.inventorymanagement.order.model.OrderDto;
import com.cts.inventorymanagement.order.model.OrderItem;
import com.cts.inventorymanagement.order.model.OrderItemDto;
import com.cts.inventorymanagement.order.repository.OrderRepository;
import com.cts.inventorymanagement.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;

    public OrderServiceImpl(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersOfCustomer(Long customerId){
    	List<Order> orders = orderRepo.findByCustomerId(customerId);
    	 return orders.stream()
                 .map(this::convertToDto)
                 .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersOfProduct(Long productId) {
        List<Order> orders = orderRepo.findByProductId(productId);
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order, "items");
        
        if (orderDto.getItems() != null) {
            List<OrderItem> orderItems = orderDto.getItems().stream()
                    .map(itemDto -> {
                        OrderItem item = new OrderItem();
                        BeanUtils.copyProperties(itemDto, item);
                        item.setOrder(order);
                        return item;
                    }).collect(Collectors.toList());
            order.setItems(orderItems);
        }
        
        orderRepo.save(order);
        return convertToDto(order);
    }

    @Override
    public Map<String, String> updateOrderStatus(Long orderId, Order.Status orderStatus) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order does not exist!"));
        order.setOrderStatus(orderStatus);
        orderRepo.save(order);

        Map<String, String> response = new HashMap<>();
        response.put("status", orderStatus.toString());
        return response;
    }
    
    
    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto, "items");
        
        if (order.getItems() != null) {
            List<OrderItemDto> itemDtos = order.getItems().stream()
                    .map(item -> {
                        OrderItemDto itemDto = new OrderItemDto();
                        BeanUtils.copyProperties(item, itemDto);
                        return itemDto;
                    }).collect(Collectors.toList());
            orderDto.setItems(itemDtos);
        }
        
        return orderDto;
    }
}