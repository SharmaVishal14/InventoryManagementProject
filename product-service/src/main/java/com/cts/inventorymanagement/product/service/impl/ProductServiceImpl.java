package com.cts.inventorymanagement.product.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.inventorymanagement.product.dto.ProductDto;
import com.cts.inventorymanagement.product.exception.ResourceNotFoundException;
import com.cts.inventorymanagement.product.model.Product;
import com.cts.inventorymanagement.product.repository.ProductRepository;
import com.cts.inventorymanagement.product.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        Product savedProduct = productRepository.save(product);
        
        ProductDto savedProductDto = new ProductDto();
        BeanUtils.copyProperties(savedProduct, savedProductDto);
        return savedProductDto;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductDto dto = new ProductDto();
                    BeanUtils.copyProperties(product, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        BeanUtils.copyProperties(productDto, existingProduct);
        existingProduct.setProductId(id); // Ensure ID is not changed
        
        Product updatedProduct = productRepository.save(existingProduct);
        
        ProductDto updatedProductDto = new ProductDto();
        BeanUtils.copyProperties(updatedProduct, updatedProductDto);
        return updatedProductDto;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> searchProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(product -> {
                    ProductDto dto = new ProductDto();
                    BeanUtils.copyProperties(product, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getLowStockProducts() {
//        // Get all products with stock level below or equal to reorder level
//        List<Product> products = productRepository.findAll().stream()
//                .filter(product -> product.getStockLevel() <= product.getReorderLevel())
//                .collect(Collectors.toList());
//                
//        return products.stream()
//                .map(product -> {
//                    ProductDto dto = new ProductDto();
//                    BeanUtils.copyProperties(product, dto);
//                    return dto;
//                })
//                .collect(Collectors.toList());
        return Arrays.asList();
    }

    @Override
    @Transactional
    public boolean updateStockLevel(Long productId, int quantity) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
//        
//        int newStockLevel = product.getStockLevel() + quantity;
//        
//        // Check if new stock level would be negative
//        if (newStockLevel < 0) {
//            return false; // Cannot have negative stock
//        }
//        
//        product.setStockLevel(newStockLevel);
//        
//        // Update product status based on stock level
//        if (newStockLevel == 0) {
//            product.setStatus(Product.ProductStatus.OUT_OF_STOCK);
//        } else {
//            product.setStatus(Product.ProductStatus.ACTIVE);
//        }
//        
//        productRepository.save(product);
        return true;
    }

	
}
