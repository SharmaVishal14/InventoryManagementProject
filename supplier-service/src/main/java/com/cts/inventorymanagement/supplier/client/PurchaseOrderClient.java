package com.cts.inventorymanagement.supplier.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.inventorymanagement.supplier.model.PurchaseOrderDto;

@FeignClient(name = "purchase-service", url = "${purchase-service.url}")
public interface PurchaseOrderClient {
    @GetMapping("/api/purchase-orders/supplier/{supplierId}")
    List<PurchaseOrderDto> getOrdersBySupplier(@PathVariable Long supplierId);
    
    @GetMapping("/api/purchase-orders/date-range")
    List<PurchaseOrderDto> getOrdersByDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    );
}