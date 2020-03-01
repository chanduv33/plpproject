package com.capgemini.storesmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;


public class CustomerInfoBean {
	private int customerId;
	private int orderId;
	private DealerInfoBean dealer;
	private ProductInfoBean product;
	private LocalDate dateOfOrder;
	private LocalDate dateOfDelivery;
	private double amount;
	private String password;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public DealerInfoBean getDealer() {
		return dealer;
	}
	public void setDealer(DealerInfoBean dealer) {
		this.dealer = dealer;
	}
	public ProductInfoBean getProduct() {
		return product;
	}
	public void setProduct(ProductInfoBean product) {
		this.product = product;
	}
	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public LocalDate getDateOfDelivery() {
		return dateOfDelivery;
	}
	public void setDateOfDelivery(LocalDate dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CustomerInfoBean [customerId=" + customerId + ", orderId=" + orderId + ", dealer=" + dealer
				+ ", product=" + product + ", dateOfOrder=" + dateOfOrder + ", dateOfDelivery=" + dateOfDelivery
				+ ", amount=" + amount + "]";
	}
	
	
}
