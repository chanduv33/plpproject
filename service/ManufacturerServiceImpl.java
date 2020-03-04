package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dao.ManufacturerDAO;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAOImpl;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class ManufacturerServiceImpl implements ManufacturerService{

	private ManufacturerDAO dao = new ManufacturerDAOImpl();
	
	/*
	 * @Override public boolean addDealer(DealerInfoBean dealer) { return
	 * dao.addDealer(dealer); }
	 */

	@Override
	public boolean setCostPrice(ProductInfoBean product,ManufacturerInfoBean bean) {
		return dao.setCostPrice(product,bean);
	}

	@Override
	public OrderDetails getPaymentDetails(int orderId,String name) {
		return dao.getPaymentDetails(orderId,name);
	}

	@Override
	public ManufacturerInfoBean login(String name, String password) {
		return dao.login(name, password);
	}

	@Override
	public boolean addProduct(ManufacturerInfoBean bean) {
		return dao.addProduct(bean);
	}

	@Override
	public List<ProductInfoBean> getAllProducts(ManufacturerInfoBean bean) {
		return dao.getAllProducts(bean);
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
	public boolean checkProductAvailability(int id, ManufacturerInfoBean bean) {
		// TODO Auto-generated method stub
		return dao.checkProductAvailability(id, bean);
	}

}
