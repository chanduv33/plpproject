package com.capgemini.storesmanagementsystem.dao;

import java.util.Iterator;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class ManufacturerDAOImpl implements ManufacturerDAO{

	@Override
	public boolean addDealer(DealerInfoBean dealer) {
		
		CollectionDbClass.dealerSet.add(dealer);
		return true;
	}

	@Override
	public boolean setCostPrice(ProductInfoBean product) {
		Iterator<ProductInfoBean> itr = CollectionDbClass.productSet.iterator();
		while(itr.hasNext()) {
			ProductInfoBean bean = itr.next();
			if(bean.getManufacturer().getProduct().getProductId() == product.getProductId()) {
				bean.getManufacturer().setProductCost(product.getCostPrice());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public ProductInfoBean getPaymentDetails(int orderId) {
		Iterator<ProductInfoBean> itr = CollectionDbClass.productSet.iterator();
		while(itr.hasNext()) {
			ProductInfoBean bean = itr.next();
			if(bean.getOrderId()==orderId) {
				return bean;
			}
		}
		return null;
	}

}
