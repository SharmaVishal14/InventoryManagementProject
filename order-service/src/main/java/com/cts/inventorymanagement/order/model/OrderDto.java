package com.cts.inventorymanagement.order.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long customerId;
    private List<OrderItemDto> items;
    private LocalDateTime orderdate;
    private Order.Status orderStatus;
}