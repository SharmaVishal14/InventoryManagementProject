package com.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.model.Category;
import com.product.model.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImpl {
	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> getAllProducts(){
		try {
			List<Product> products = productRepo.findAll();
			return products;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Product> getProductsByCategory(Category category){
		try {
			List<Product> products = productRepo.findProductsByCategory(category);
			return products;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Product getProductById(int id){
		try {
			Product product = productRepo.findById(id).orElse(new Product());
			return product;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void addProduct(Product product) {
		try {
			productRepo.save(product);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void deleteProductById(int id) {
		try {
			productRepo.deleteById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
