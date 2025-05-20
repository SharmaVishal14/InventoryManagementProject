package com.cts.inventorymanagement.supplier.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.inventorymanagement.supplier.client.ProductClient;
import com.cts.inventorymanagement.supplier.client.PurchaseOrderClient;
import com.cts.inventorymanagement.supplier.exceptions.SupplierNotFoundException;
import com.cts.inventorymanagement.supplier.model.ProductDto;
import com.cts.inventorymanagement.supplier.model.PurchaseOrderDto;
import com.cts.inventorymanagement.supplier.model.Supplier;
import com.cts.inventorymanagement.supplier.model.SupplierDetailsResponse;
import com.cts.inventorymanagement.supplier.model.SupplierRequest;
import com.cts.inventorymanagement.supplier.model.SupplierResponse;
import com.cts.inventorymanagement.supplier.repository.SupplierRepository;
import com.cts.inventorymanagement.supplier.service.SupplierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;
    private final ProductClient productClient;
    private final PurchaseOrderClient purchaseClient;

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setContactInfo(request.getContactInfo());
        supplier.setProductsSupplied(request.getProductsSupplied());
        return convertToResponse(repository.save(supplier));
    }

    @Override
    public SupplierDetailsResponse getSupplierDetails(Long id) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found with id: " + id));
        
        List<ProductDto> products = supplier.getProductsSupplied().stream()
                .map(productClient::getProductById)
                .collect(Collectors.toList());
        
        List<PurchaseOrderDto> orders = purchaseClient.getOrdersBySupplier(id);
        
        return new SupplierDetailsResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactInfo(),
                products,
                orders
        );
    }

    @Override
    public List<PurchaseOrderDto> getSupplierOrdersBetweenDates(Long supplierId, LocalDate startDate, LocalDate endDate) {
        List<PurchaseOrderDto> allOrders = purchaseClient.getOrdersBySupplier(supplierId);
        return allOrders.stream()
                .filter(order -> order.getOrderDate().isAfter(startDate.minusDays(1)) &&
                                order.getOrderDate().isBefore(endDate.plusDays(1)))
                .collect(Collectors.toList());
    }

    private SupplierResponse convertToResponse(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactInfo(),
                supplier.getProductsSupplied()
        );
    }
}