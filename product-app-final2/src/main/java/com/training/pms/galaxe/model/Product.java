package com.training.pms.galaxe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name ="freshproducts")
@Component
public class Product {

	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	private int productId;
	private String productName;
	private int quantityInHand;
	private int price;
	public Product(int productId, String productName, int quantityInHand, int price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantityInHand = quantityInHand;
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantityInHand() {
		return quantityInHand;
	}
	public void setQuantityInHand(int quantityInHand) {
		this.quantityInHand = quantityInHand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", quantityInHand=" + quantityInHand
				+ ", price=" + price + "]";
	}
	
	
	
}
