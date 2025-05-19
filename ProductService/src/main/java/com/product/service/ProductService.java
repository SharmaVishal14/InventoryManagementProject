package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.model.Product;
import com.product.model.Category;
import com.product.repository.ProductRepository;

public interface ProductService {
	public List<Product> getAllProducts();
	public List<Product> getProductsByCategory(Category category);
	public Product getProductById(int id);
	public void addProduct(Product product);
	public void deleteProductById(int id);
}
