package com.cognizant.repository;

import com.cognizant.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}
