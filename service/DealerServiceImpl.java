package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dao.DealerDAO;
import com.capgemini.storesmanagementsystem.dao.DealerDAOImpl;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class DealerServiceImpl implements DealerService{
	
	private DealerDAO dao = new DealerDAOImpl();

	@Override
	public boolean placeOrder(ProductInfoBean product, int quantity,int id) {
		return dao.placeOrder(product, quantity,id);
	}

	@Override
	public boolean setSellingPrice(DealerInfoBean dealer,int id) {
		return dao.setSellingPrice(dealer,id);
	}
}
