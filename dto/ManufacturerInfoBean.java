package com.capgemini.storesmanagementsystem.dto;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class ManufacturerInfoBean {
	private String manufacturerName;
	@lombok.EqualsAndHashCode.Exclude
	private ProductInfoBean product; 
	private String description;
	private double productCost;
	private DealerInfoBean dealer;
	private int manufacturerId;
	
	public ManufacturerInfoBean (){
		
	}
	
}
