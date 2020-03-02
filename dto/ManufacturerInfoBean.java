package com.capgemini.storesmanagementsystem.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.ToString.Exclude;

public class ManufacturerInfoBean {
	private String manufacturerName;
	private List<ProductInfoBean> product = new LinkedList<ProductInfoBean>(); 
	private String description;
	private double productCost;
	private int manufacturerId;
	private String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ManufacturerInfoBean (){
		
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public List<ProductInfoBean> getProduct() {
		return product;
	}
	public void setProduct(List<ProductInfoBean> product) {
		this.product = product;
	}
}
