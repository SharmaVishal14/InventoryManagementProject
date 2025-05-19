package com.stock.service;

import com.stock.model.Stock;

public interface StockService {
	public Stock getStock(int product_id);
	public void addStock(int product_id,int quantity);
	public void decrementStock(int product_id,int quantity);
}
