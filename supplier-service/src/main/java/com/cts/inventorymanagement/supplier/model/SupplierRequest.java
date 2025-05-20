package com.cts.inventorymanagement.supplier.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierRequest {
    @NotBlank
    private String name;
    private String contactInfo;
    private List<Long> productsSupplied;
}