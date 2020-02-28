package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dao.AdminDAO;
import com.capgemini.storesmanagementsystem.dao.AdminDAOImpl;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;

public class AdminServiceImpl implements AdminService{
	
	private AdminDAO dao = new AdminDAOImpl();

	@Override
	public boolean addManufacturer(ManufacturerInfoBean manufacturer) {
		System.out.println(manufacturer);
		return dao.addManufacturer(manufacturer);
	}

	@Override
	public ManufacturerInfoBean updateManufacturerDetails(ManufacturerInfoBean manufacturer) {
		return dao.updateManufacturerDetails(manufacturer);
	}

	@Override
	public ManufacturerInfoBean getManufacturerDetails(int id) {
		return dao.getManufacturerDetails(id);
	}

	@Override
	public List<ManufacturerInfoBean> getAllManufacturersDetails() {
		return dao.getAllManufacturersDetails();
	}

}
