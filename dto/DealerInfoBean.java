package com.capgemini.storesmanagementsystem.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class DealerInfoBean {
	private String dealerName;
	@Exclude
	private ManufacturerInfoBean manufacturer;
	private double sellingPrice;
	private int quantity;
	private final int minimumQuantity = 10;
	@Exclude
	
	private List<ProductInfoBean> product;
	private int dealerId;
	public DealerInfoBean() {
	}
}
