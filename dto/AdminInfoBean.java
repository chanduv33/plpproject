package com.capgemini.storesmanagementsystem.dto;

import lombok.Data;
import lombok.ToString.Exclude;


public class AdminInfoBean {
	private static int adminId;
	private String adminName;
	
	private ManufacturerInfoBean manufacturer;
	public AdminInfoBean() {
		
	}
	public static int getAdminId() {
		return adminId;
	}
	public static void setAdminId(int adminId) {
		AdminInfoBean.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public ManufacturerInfoBean getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(ManufacturerInfoBean manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	
}
