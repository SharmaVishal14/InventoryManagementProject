package com.cts.inventorymanagement.supplier.service;

import java.time.LocalDate;
import java.util.List;

import com.cts.inventorymanagement.supplier.model.PurchaseOrderDto;
import com.cts.inventorymanagement.supplier.model.SupplierDetailsResponse;
import com.cts.inventorymanagement.supplier.model.SupplierRequest;
import com.cts.inventorymanagement.supplier.model.SupplierResponse;

public interface SupplierService {

	SupplierResponse createSupplier(SupplierRequest request);

	SupplierDetailsResponse getSupplierDetails(Long id);

	List<PurchaseOrderDto> getSupplierOrdersBetweenDates(Long supplierId, LocalDate startDate, LocalDate endDate);

}