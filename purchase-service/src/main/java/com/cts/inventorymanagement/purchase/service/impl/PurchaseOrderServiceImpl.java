package com.cts.inventorymanagement.purchase.service.impl;

import com.cts.inventorymanagement.purchase.model.*;
import com.cts.inventorymanagement.purchase.repository.PurchaseOrderRepository;
import com.cts.inventorymanagement.purchase.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository repository;

    @Override
    @Transactional
    public PurchaseOrderDto createOrder(PurchaseOrderRequest request) {
        PurchaseOrder order = new PurchaseOrder();
        order.setSupplierId(request.getSupplierId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setOrderDate(request.getOrderDate());
        order.setDeliveryDate(request.getDeliveryDate());
        
        return convertToDto(repository.save(order));
    }

    @Override
    public PurchaseOrderDto getOrderById(Long id) {
        return convertToDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @Override
    public List<PurchaseOrderDto> getAllOrders() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PurchaseOrderDto updateOrderStatus(Long id, OrderStatus status) {
        PurchaseOrder order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        if(status == OrderStatus.DELIVERED) {
            order.setDeliveryDate(LocalDate.now());
        }
        order.setStatus(status);
        return convertToDto(repository.save(order));
    }

    @Override
    public List<PurchaseOrderDto> getOrdersBySupplier(Long supplierId) {
        return repository.findBySupplierId(supplierId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseOrderDto> getOrdersByProduct(Long productId) {
        return repository.findByProductId(productId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseOrderDto> getOrdersBetweenDates(LocalDate start, LocalDate end) {
        return repository.findByOrderDateBetween(start, end).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }

    private PurchaseOrderDto convertToDto(PurchaseOrder order) {
        PurchaseOrderDto Dto = new PurchaseOrderDto();
        Dto.setId(order.getId());
        Dto.setSupplierId(order.getSupplierId());
        Dto.setProductId(order.getProductId());
        Dto.setQuantity(order.getQuantity());
        Dto.setOrderDate(order.getOrderDate());
        Dto.setDeliveryDate(order.getDeliveryDate());
        Dto.setStatus(order.getStatus());
        return Dto;
    }
}