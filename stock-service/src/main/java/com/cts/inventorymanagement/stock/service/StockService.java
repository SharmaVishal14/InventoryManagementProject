package com.cts.inventorymanagement.stock.service;

import com.cts.inventorymanagement.stock.model.StockDto;
import com.cts.inventorymanagement.stock.model.StockUpdateRequest;
import java.util.List;

public interface StockService {
    StockDto getStock(Long productId);
    StockDto updateStock(Long productId, StockUpdateRequest request);
    StockDto addStock(StockDto stockDto);
    List<StockDto> getLowStockItems();
}