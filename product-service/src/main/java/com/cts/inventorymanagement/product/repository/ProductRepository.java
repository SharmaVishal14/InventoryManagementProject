package com.cts.inventorymanagement.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.inventorymanagement.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByStatus(Product.ProductStatus status);
    List<Product> findByCategory(Product.ProductCategory category);
}