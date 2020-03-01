package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dao.ManufacturerDAO;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAOImpl;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class ManufacturerServiceImpl implements ManufacturerService{

	private ManufacturerDAO dao = new ManufacturerDAOImpl();
	
	@Override
	public boolean addDealer(DealerInfoBean dealer) {
		return dao.addDealer(dealer);
	}

	@Override
	public boolean setCostPrice(ProductInfoBean product) {
		return dao.setCostPrice(product);
	}

	@Override
	public ProductInfoBean getPaymentDetails(int orderId) {
		return dao.getPaymentDetails(orderId);
	}

	@Override
	public ManufacturerInfoBean login(String name, String password) {
		return dao.login(name, password);
	}

}
