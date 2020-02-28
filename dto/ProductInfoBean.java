package com.capgemini.storesmanagementsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class ProductInfoBean {
	
	
	private int productId;
	private  int orderId;
	private String productName;
	private double costPrice;
	@Exclude
	@lombok.EqualsAndHashCode.Exclude
	private DealerInfoBean dealer;
	private LocalDate dateOfOrder;
	private double amount;
	private LocalDate dateOfDelivery;
	@Exclude
	private ManufacturerInfoBean manufacturer;
	
	public ProductInfoBean() {
		
	}
	
}
