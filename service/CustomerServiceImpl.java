package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dao.CustomerDAO;
import com.capgemini.storesmanagementsystem.dao.CustomerDAOImpl;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDAO dao= new CustomerDAOImpl();
	
	@Override
	public boolean buyProduct(DealerInfoBean dealer,OrderDetails orders,CustomerInfoBean customer,String pname) {
		return dao.buyProduct(dealer,orders,customer,pname);
	}

	@Override
	public OrderDetails getOrderDetails(int id,CustomerInfoBean customer) {
		return dao.getOrderDetails(id,customer);
	}

	@Override
	public CustomerInfoBean login(int id, String password) {
		return dao.login(id, password);
	}

	@Override
	public boolean checkEmailAvailability(String email) {
		return dao.checkEmailAvailability(email);
	}

	@Override
	public boolean checkIdAvailability(int id) {
		return dao.checkIdAvailability(id);
	}

	@Override
	public void autoBuy(DealerInfoBean bean, String name) {
		dao.autoBuy(bean, name);
		
	}

	@Override
	public boolean setDeliveredDate(String date,int id,CustomerInfoBean customer) {
		return dao.setDeliveredDate(date, id, customer);
	}
	
}
