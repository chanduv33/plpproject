package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dao.DealerDAO;
import com.capgemini.storesmanagementsystem.dao.DealerDAOImpl;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class DealerServiceImpl implements DealerService{
	
	private DealerDAO dao = new DealerDAOImpl();

	@Override
	public boolean placeOrder(OrderDetails order, DealerInfoBean dealer) {
		return dao.placeOrder(order, dealer);
	}

	@Override
	public boolean setSellingPrice(DealerInfoBean dealer,int id,double price) {
		return dao.setSellingPrice(dealer,id,price);
	}

	@Override
	public int getNumberOfProducts(String name, DealerInfoBean dealer) {
		return dao.getNumberOfProducts(name, dealer);
	}

	@Override
	public DealerInfoBean login(String name, String password) {
		return dao.login(name, password);
	}

	@Override
	public boolean register(DealerInfoBean bean) {
		return dao.register(bean);
	}

	@Override
	public OrderDetails getPaymentDeatils(int oid,DealerInfoBean dealer) {
		return dao.getPaymentDeatils(oid,dealer);
	}

	@Override
	public boolean checkIdAvailability(int id) {
		return dao.checkIdAvailability(id);
	}

	@Override
	public boolean checkNameAvailability(String name) {
		return dao.checkNameAvailability(name);
	}

	@Override
	public boolean setDeliveredDate(String date,int id,DealerInfoBean dealer) {
		return dao.setDeliveredDate(date,id,dealer);
	}
}
