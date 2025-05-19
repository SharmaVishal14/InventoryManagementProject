package com.stock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	int product_id;
	int quantity;
	int reorderlevel;
	
	public Stock() {
	}
	
	public Stock(int id, int product_id, int quantity, int reorderlevel) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.reorderlevel = reorderlevel;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getReorderlevel() {
		return reorderlevel;
	}
	public void setReorderlevel(int reorderlevel) {
		this.reorderlevel = reorderlevel;
	}
	
}
