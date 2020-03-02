package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dao.DealerDAO;
import com.capgemini.storesmanagementsystem.dao.DealerDAOImpl;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class DealerServiceImpl implements DealerService{
	
	private DealerDAO dao = new DealerDAOImpl();

	@Override
	public boolean placeOrder(ProductInfoBean product, DealerInfoBean dealer,ManufacturerInfoBean manufacturer) {
		return dao.placeOrder(product, dealer,manufacturer);
	}

	@Override
	public boolean setSellingPrice(DealerInfoBean dealer,int id,double price) {
		return dao.setSellingPrice(dealer,id,price);
	}

	@Override
	public List<ProductInfoBean> getAllProducts(int id) {
		return dao.getAllProducts(id);
	}

	@Override
	public int getNumberOfProducts(String name, int id) {
		return dao.getNumberOfProducts(name, id);
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
	public ProductInfoBean getPaymentDeatils(int oid,DealerInfoBean dealer) {
		return dao.getPaymentDeatils(oid,dealer);
	}
}
