package com.capgemini.storesmanagementsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString.Exclude;

public class ProductInfoBean {
	
	
	private int productId;
	private  int orderId;
	private String productName;
	private double costPrice;
	private DealerInfoBean dealer;
	private LocalDate dateOfOrder;
	private double amount;
	private LocalDate dateOfDelivery;
	private ManufacturerInfoBean manufacturer;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductInfoBean() {
		
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public DealerInfoBean getDealer() {
		return dealer;
	}

	public void setDealer(DealerInfoBean dealer) {
		this.dealer = dealer;
	}

	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(LocalDate dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public ManufacturerInfoBean getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerInfoBean manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
