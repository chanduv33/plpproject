package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dao.CustomerDAO;
import com.capgemini.storesmanagementsystem.dao.CustomerDAOImpl;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDAO dao= new CustomerDAOImpl();
	
	@Override
	public boolean buyProduct(DealerInfoBean dealer,CustomerInfoBean customer,String pname) {
		return dao.buyProduct(dealer,customer,pname);
	}

	@Override
	public CustomerInfoBean getOrderDetails(int id) {
		return dao.getOrderDetails(id);
	}

	@Override
	public CustomerInfoBean login(int id, String password) {
		return dao.login(id, password);
	}
	
}
