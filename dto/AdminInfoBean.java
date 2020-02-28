package com.capgemini.storesmanagementsystem.dto;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class AdminInfoBean {
	private static int adminId;
	private String adminName;
	@Exclude
	private ManufacturerInfoBean manufacturer;
	public AdminInfoBean() {
		
	}
}
