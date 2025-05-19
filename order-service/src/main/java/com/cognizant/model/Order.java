package com.cognizant.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    private Long customerId;
    private LocalDate orderDate;
    private String status;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();
    
    public Order() {
    }
 
    public Order(Long customerId, LocalDate orderDate, String status) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
    }
 
    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }
 
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
 
    public Long getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
 
    public LocalDate getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public List<OrderItem> getItems() {
        return items;
    }
 
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
 
    // Helper method to manage bidirectional relationship
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
    @PrePersist
    @PreUpdate
    private void validate() {
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("Order must have at least one item");
        }
    }
}