package com.cognizant.repository;

import com.cognizant.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
