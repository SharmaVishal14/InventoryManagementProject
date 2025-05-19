package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.model.Stock;
import com.stock.service.StockService;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
	
	@Autowired
	private StockService service;
	
	@GetMapping("/{product_id}")
	public ResponseEntity<Stock> getStock(@PathVariable int product_id){
		return new ResponseEntity<>(service.getStock(product_id),HttpStatus.OK);
	}
	
	@PutMapping("/add/{product_id}")
	public ResponseEntity<Void> addStock(@PathVariable int product_id, @RequestParam int quantity){
		try {
			service.addStock(product_id, quantity);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	@PutMapping("/decrement/{product_id}")
	public ResponseEntity<Void> decrementStock(@PathVariable int product_id, @RequestParam int quantity){
		try {
			service.decrementStock(product_id, quantity);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
}
