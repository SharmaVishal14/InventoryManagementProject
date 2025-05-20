package com.cts.inventorymanagement.product.service;

import com.cts.inventorymanagement.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    List<ProductDto> searchProductsByName(String name);
    List<ProductDto> getLowStockProducts();
    boolean updateStockLevel(Long productId, int quantity);
}