package com.capgemini.storesmanagementsystem.dto;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class OrderDetails {
	private int orderId;
	private LocalDate deliveredOn;
	private LocalDate dateOfOrder;
	private double amount;
	private LocalDate dateOfDelivery;
	private String productName;
	private String status;
	private List<CustomerInfoBean> customers = new LinkedList<CustomerInfoBean>();
	private List<DealerInfoBean> dealers = new LinkedList<DealerInfoBean>();

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

	public List<CustomerInfoBean> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerInfoBean> customers) {
		this.customers = customers;
	}

	public List<DealerInfoBean> getDealers() {
		return dealers;
	}

	public void setDealers(List<DealerInfoBean> dealers) {
		this.dealers = dealers;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getDeliveredOn() {
		return deliveredOn;
	}

	public void setDeliveredOn(LocalDate deliveredOn) {
		this.deliveredOn = deliveredOn;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
