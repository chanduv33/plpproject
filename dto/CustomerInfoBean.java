package com.capgemini.storesmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;


public class CustomerInfoBean {
	private int customerId;
	private String password;
	private String email;
	
	private List<OrderDetails> orders = new LinkedList<OrderDetails>();
	
	public List<OrderDetails> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}
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
}
