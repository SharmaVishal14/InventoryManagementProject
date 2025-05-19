package com.stock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stock.model.Stock;

@Repository
public interface StockRepo extends JpaRepository<Stock, Integer> {
	@Query("SELECT id,product_id,quantity,reorderlevel FROM stock WHERE product_id = :product_id")
	public Stock findStockByProductId(int product_id);
}
