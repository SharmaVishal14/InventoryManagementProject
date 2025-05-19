package com.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.model.Stock;
import com.stock.repo.StockRepo;
import com.stock.service.StockService;

@Service
@Transactional
public class StockServiceImpl implements StockService{

	@Autowired
	private StockRepo stockRepo;
	
	@Override
	public Stock getStock(int product_id) {
		return stockRepo.findStockByProductId(product_id);
	}
	
	@Override
	public void addStock(int product_id,int quantity) {
		Stock stock = stockRepo.findStockByProductId(product_id);
		if(stock==null) {
			stock = new Stock();
			stock.setProduct_id(product_id);
			stock.setReorderlevel(10);
		}
		stock.setQuantity(stock.getQuantity()+quantity);
		
		stockRepo.save(stock);	
	}

	@Override
	public void decrementStock(int product_id,int quantity) {
		Stock stock = stockRepo.findStockByProductId(product_id);
		if(stock==null || stock.getQuantity()<quantity) {
			throw new RuntimeException("Cannot decrement the required quantity, stock unavailable!!");
		}
		
		stock.setQuantity(stock.getQuantity()-quantity);
		stockRepo.save(stock);
	}
	
}
