package com.capgemini.storesmanagementsystem.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString.Exclude;

public class DealerInfoBean {
	private String dealerName;
	private ManufacturerInfoBean manufacturer;
	private final int minimumQuantity = 10;
	private List<ProductInfoBean> product = new ArrayList<ProductInfoBean>();
	private int dealerId;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DealerInfoBean() {
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public ManufacturerInfoBean getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(ManufacturerInfoBean manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public List<ProductInfoBean> getProduct() {
		return product;
	}
	public void setProduct(List<ProductInfoBean> product) {
		this.product = product;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getMinimumQuantity() {
		return minimumQuantity;
	}
	
	
}
