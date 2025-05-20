package com.cts.inventorymanagement.purchase.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PurchaseOrderDto {
    private Long id;
    private Long supplierId;
    private Long productId;
    private Integer quantity;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private OrderStatus status;
}