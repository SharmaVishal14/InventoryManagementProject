package com.product.repository;

import java.util.List;
import com.product.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findProductsByCategory(Category category);
}
