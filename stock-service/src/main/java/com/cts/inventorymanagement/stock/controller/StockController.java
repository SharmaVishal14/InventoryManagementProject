package com.cts.inventorymanagement.stock.controller;

import com.cts.inventorymanagement.stock.model.*;
import com.cts.inventorymanagement.stock.service.StockService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{productId}")
    public StockDto getStock(@PathVariable Long productId) {
        return stockService.getStock(productId);
    }

    @PutMapping("/{productId}")
    public StockDto updateStock(
            @PathVariable Long productId,
            @Valid @RequestBody StockUpdateRequest request) {
        return stockService.updateStock(productId, request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockDto addStock(@Valid @RequestBody StockDto stockDto) {
        return stockService.addStock(stockDto);
    }

    @GetMapping("/low")
    public List<StockDto> getLowStockItems() {
        return stockService.getLowStockItems();
    }
}