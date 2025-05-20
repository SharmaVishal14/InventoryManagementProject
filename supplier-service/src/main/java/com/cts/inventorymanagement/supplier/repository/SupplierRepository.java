package com.cts.inventorymanagement.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.inventorymanagement.supplier.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}