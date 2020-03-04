package com.capgemini.storesmanagementsystem.dao;

import java.util.Iterator;
import java.util.List;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.service.DealerService;
import com.capgemini.storesmanagementsystem.service.DealerServiceImpl;

public class ManufacturerDAOImpl implements ManufacturerDAO {
	DealerService dealerSer = new DealerServiceImpl();
	/*
	 * @Override public boolean addDealer(DealerInfoBean dealer) {
	 * 
	 * CollectionDbClass.dealerSet.add(dealer); return true; }
	 */

	@Override
	public ManufacturerInfoBean login(String name, String password) {
		Iterator<ManufacturerInfoBean> itr = CollectionDbClass.manufacturerSet.iterator();
		while (itr.hasNext()) {
			ManufacturerInfoBean bean = itr.next();
			if (bean.getManufacturerName().equalsIgnoreCase(name) && bean.getPassword().equals(password)) {
				return bean;
			}
		}
		return null;
	}

	@Override
	public boolean setCostPrice(ProductInfoBean product,ManufacturerInfoBean bean) {
		
		Iterator<ProductInfoBean> itr = bean.getProduct().iterator();
		while(itr.hasNext()) {
			ProductInfoBean prod = itr.next();
			if(prod.getProductId()==product.getProductId()) {
				prod.setCostPrice(product.getCostPrice());
				return true;
			}
		}
		return false;
	}

	@Override
	public OrderDetails getPaymentDetails(int orderId,String name) {
		for (DealerInfoBean dealer : CollectionDbClass.dealerSet) {
			if(dealer.getDealerName().equalsIgnoreCase(name)) {
				return dealerSer.getPaymentDeatils(orderId, dealer);
			}
		}
		return null;
	}

	@Override
	public boolean addProduct(ManufacturerInfoBean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductInfoBean> getAllProducts(ManufacturerInfoBean bean) {
		for (ManufacturerInfoBean man : CollectionDbClass.manufacturerSet) {
			if(man.getManufacturerName().equalsIgnoreCase(bean.getManufacturerName())) {
				return man.getProduct();
			}
		}
		return null;
	}

	@Override
	public boolean checkIdAvailability(int id) {
		Iterator<ManufacturerInfoBean> itr = CollectionDbClass.manufacturerSet.iterator();
		if(itr.hasNext()) {
		while (itr.hasNext()) {
			ManufacturerInfoBean bean = itr.next();
			if (bean.getManufacturerId()==id) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	 } else {
		return true;
	}
	}
	
	@Override
	public boolean checkNameAvailability(String name) {
		Iterator<ManufacturerInfoBean> itr = CollectionDbClass.manufacturerSet.iterator();
		if(itr.hasNext()) {
		while (itr.hasNext()) {
			ManufacturerInfoBean bean = itr.next();
			if (bean.getManufacturerName().equalsIgnoreCase(name)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	 } else {
		return true;
	}
	}

	@Override
	public boolean checkProductAvailability(int id,ManufacturerInfoBean bean) {
		Iterator<ProductInfoBean> itr = bean.getProduct().iterator();
		if(itr.hasNext()) {
		while (itr.hasNext()) {
			ProductInfoBean prod = itr.next();
			if (prod.getProductId()==id) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	 } else {
		return true;
	}
	}

}
