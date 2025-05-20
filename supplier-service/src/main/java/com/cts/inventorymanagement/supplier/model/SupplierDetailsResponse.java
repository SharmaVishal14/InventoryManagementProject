package com.cts.inventorymanagement.supplier.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDetailsResponse {
    private Long id;
    private String name;
    private String contactInfo;
    private List<ProductDto> productsDetails;
    private List<PurchaseOrderDto> purchaseOrders;
}