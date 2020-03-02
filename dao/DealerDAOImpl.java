package com.capgemini.storesmanagementsystem.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class DealerDAOImpl implements DealerDAO {

	@Override
	public boolean placeOrder(ProductInfoBean product, DealerInfoBean bean,ManufacturerInfoBean manufacturer) {
				ProductInfoBean prod = new ProductInfoBean();
				prod.setProductName(product.getProductName());
				prod.setOrderId(product.getOrderId());
				prod.setQuantity(product.getQuantity());
				LocalDate date = LocalDate.now();
				prod.setDateOfOrder(date);
				prod.setDateOfDelivery(LocalDate.now().plusDays(2));
				prod.setDealer(bean);
				prod.setManufacturer(product.getManufacturer());
				prod.setSellingPrice(product.getCostPrice() + 50);
				bean.getProduct().add(prod);
				prod.setAmount(product.getQuantity() * product.getCostPrice());
				
				return true;
	}

	@Override
	public DealerInfoBean login(String name, String password) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerName().equalsIgnoreCase(name) && bean.getPassword().equals(password)) {
				return bean;
			}
		}
		return null;
	}

	@Override
	public boolean setSellingPrice(DealerInfoBean dealer, int id,double price) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerName().equals(dealer.getDealerName())) {
				for (ProductInfoBean prod : bean.getProduct()) {
					if (prod != null) {
						if (prod.getProductId() == id) {
							prod.setSellingPrice(price);
							return true;
						}
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	public void checkQuantity(DealerInfoBean dealer, String name) {

		for (ProductInfoBean prods : dealer.getProduct()) {

			if (prods.getProductName().equals(name)) {
				int quantity = prods.getQuantity() - 1;
				prods.setQuantity(quantity);
				if (prods.getQuantity() <= dealer.getMinimumQuantity()) {
					prods.setQuantity(prods.getQuantity() * 2);
					// autoBuyStocks(prods, prods.getQuantity() * 2);
				}
			}
		}
	}

	/*
	 * public void autoBuyStocks(ProductInfoBean product, int quantity) { int newOid
	 * = product.getOrderId() + 1; placeOrder(product, quantity, newOid); }
	 */

	@Override
	public List<ProductInfoBean> getAllProducts(int id) {
		for (DealerInfoBean dealer : CollectionDbClass.dealerSet) {
			if (dealer.getDealerId() == id)
				return dealer.getProduct();
			else
				return null;
		}
		return null;
	}

	@Override
	public int getNumberOfProducts(String name, int id) {
		for (DealerInfoBean dealer : CollectionDbClass.dealerSet) {
			if (dealer.getDealerId() == id) {
				for (ProductInfoBean prod : dealer.getProduct()) {
					if (prod.getProductName().equals(name))
						return prod.getQuantity();
				}
			}
		}
		return 0;
	}

	@Override
	public boolean register(DealerInfoBean bean) {
		CollectionDbClass.dealerSet.add(bean);
		return true;
	}

	@Override
	public ProductInfoBean getPaymentDeatils(int oid,DealerInfoBean dealer) {
		for (ProductInfoBean prod : dealer.getProduct()) {
			if(prod.getOrderId()==oid) {
				return prod;
			}
		}
		return null;
	}
	
	
	
}
