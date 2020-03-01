package com.capgemini.storesmanagementsystem.dto;

import lombok.Data;
import lombok.ToString.Exclude;

public class ManufacturerInfoBean {
	private String manufacturerName;
	private ProductInfoBean product; 
	private String description;
	private double productCost;
	private DealerInfoBean dealer;
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

	public ProductInfoBean getProduct() {
		return product;
	}

	public void setProduct(ProductInfoBean product) {
		this.product = product;
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

	public DealerInfoBean getDealer() {
		return dealer;
	}

	public void setDealer(DealerInfoBean dealer) {
		this.dealer = dealer;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	@Override
	public String toString() {
		return "ManufacturerInfoBean [manufacturerName=" + manufacturerName + ", product=" + product + ", description="
				+ description + ", productCost=" + productCost + ", dealer=" + dealer + ", manufacturerId="
				+ manufacturerId + "]";
	}
	
	
	
	
}
