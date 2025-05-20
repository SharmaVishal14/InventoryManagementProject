package com.cts.inventorymanagement.stock.service.impl;

import com.cts.inventorymanagement.stock.exceptions.InsufficientStockException;
import com.cts.inventorymanagement.stock.exceptions.StockNotFoundException;
import com.cts.inventorymanagement.stock.model.*;
import com.cts.inventorymanagement.stock.repository.StockRepository;
import com.cts.inventorymanagement.stock.service.StockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public StockDto getStock(Long productId) {
        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() -> new StockNotFoundException(
                    "Stock not found for product: " + productId));
        return convertToDto(stock);
    }

    @Override
    @Transactional
    public StockDto updateStock(Long productId, StockUpdateRequest request) {
        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() -> new StockNotFoundException(
                    "Stock not found for product: " + productId));

        switch (request.getOperation()) {
            case INCREMENT -> stock.setQuantity(stock.getQuantity() + request.getQuantity());
            case DECREMENT -> {
                int newQuantity = stock.getQuantity() - request.getQuantity();
                if (newQuantity < 0) {
                    throw new InsufficientStockException(
                        "Insufficient stock for product: " + productId);
                }
                stock.setQuantity(newQuantity);
            }
        }

        Stock updatedStock = stockRepository.save(stock);
        return convertToDto(updatedStock);
    }
    
    @Override
    public StockDto addStock(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setProductId(stockDto.getProductId());
        stock.setQuantity(stockDto.getQuantity());
        stock.setReorderLevel(stockDto.getReorderLevel());
        Stock savedStock = stockRepository.save(stock);
        return convertToDto(savedStock);
    }

    @Override
    public List<StockDto> getLowStockItems() {
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getQuantity() < stock.getReorderLevel())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StockDto convertToDto(Stock stock) {
        StockDto Dto = new StockDto();
        Dto.setProductId(stock.getProductId());
        Dto.setQuantity(stock.getQuantity());
        Dto.setReorderLevel(stock.getReorderLevel());
        Dto.setLowStock(stock.getQuantity() < stock.getReorderLevel());
        return Dto;
    }
}