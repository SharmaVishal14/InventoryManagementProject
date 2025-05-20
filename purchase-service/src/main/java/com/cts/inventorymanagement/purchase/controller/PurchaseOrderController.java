package com.cts.inventorymanagement.purchase.controller;

import com.cts.inventorymanagement.purchase.model.*;
import com.cts.inventorymanagement.purchase.service.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService orderService;

    public PurchaseOrderController(PurchaseOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrderDto createOrder(@Valid @RequestBody PurchaseOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public PurchaseOrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<PurchaseOrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PatchMapping("/{id}/status")
    public PurchaseOrderDto updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(id, status);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<PurchaseOrderDto> getOrdersBySupplierId(@PathVariable Long supplierId) {
        return orderService.getOrdersBySupplier(supplierId);
    }

    @GetMapping("/product/{productId}")
    public List<PurchaseOrderDto> getOrdersByProductId(@PathVariable Long productId) {
        return orderService.getOrdersByProduct(productId);
    }

    @GetMapping("/date-range")
    public List<PurchaseOrderDto> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return orderService.getOrdersBetweenDates(startDate, endDate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}