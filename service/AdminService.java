package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;

public interface AdminService {
	
	public boolean addManufacturer(ManufacturerInfoBean manufacturer);
	public ManufacturerInfoBean updateManufacturerDetails(ManufacturerInfoBean manufacturer);
	public ManufacturerInfoBean getManufacturerDetails(int id);
	public List<ManufacturerInfoBean> getAllManufacturersDetails();
}
