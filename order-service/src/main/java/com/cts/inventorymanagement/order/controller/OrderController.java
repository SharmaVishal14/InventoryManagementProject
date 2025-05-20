package com.cts.inventorymanagement.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.inventorymanagement.order.model.Order;
import com.cts.inventorymanagement.order.model.OrderDto;
import com.cts.inventorymanagement.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(@RequestParam(required = false) Long customerId) {
    	List<OrderDto> orders = new ArrayList<>();
    	if(customerId==null) {
    		orders = service.getAllOrders();    		
    	}
    	else {
    		orders = service.getOrdersOfCustomer(customerId);
    	}
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getOrdersOfProduct(productId));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(service.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Map<String, String>> updateOrderStatus(@PathVariable Long orderId, @RequestParam String orderStatus) {
        return ResponseEntity.ok(service.updateOrderStatus(orderId, Order.Status.valueOf(orderStatus)));
    }
}