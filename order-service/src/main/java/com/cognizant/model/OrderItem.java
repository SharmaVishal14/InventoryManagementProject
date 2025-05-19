package com.cognizant.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
 
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
    
    private Long productId;
    @Column(nullable = false)
    private int quantity=1;
    
    public OrderItem() {
    }
 
    public OrderItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
 
    // Getters and Setters
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
this.id = id;
    }
 
    public Order getOrder() {
        return order;
    }
 
    public void setOrder(Order order) {
        this.order = order;
    }
 
    public Long getProductId() {
        return productId;
    }
 
    public void setProductId(Long productId) {
        this.productId = productId;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
//    public double getPrice() {
//        return price;
//    }
 
}