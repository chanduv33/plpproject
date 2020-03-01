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
	public boolean placeOrder(ProductInfoBean product, int quantity, int id) {

		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerId() == id) {
				product.setQuantity(quantity);
				LocalDate date = LocalDate.now();
				product.setDateOfOrder(date);
				product.setDateOfDelivery(LocalDate.now().plusDays(2));
				product.setDealer(bean);
				bean.getProduct().add(product);
				product.setAmount(quantity * product.getCostPrice());
				System.out.println(bean.getProduct());
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public DealerInfoBean login(String name, String password) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if(bean.getDealerName().equalsIgnoreCase(name) && bean.getPassword().equals(password)) {
				return bean;
			}
		}
		return null;
	}

	@Override
	public boolean setSellingPrice(DealerInfoBean dealer, int id) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerId() == dealer.getDealerId()) {
				for (ProductInfoBean prod : bean.getProduct()) {
					if(prod!=null) {
					if (prod.getProductId() == id) {
						prod.getDealer().setSellingPrice(dealer.getSellingPrice());
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
					//autoBuyStocks(prods, prods.getQuantity() * 2);
				}
			}
		}
	}

	public void autoBuyStocks(ProductInfoBean product, int quantity) {
		int newOid = product.getOrderId() + 1;
		placeOrder(product, quantity, newOid);
	}

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
	public int getNumberOfProducts(String name,int id) {
		for (DealerInfoBean dealer : CollectionDbClass.dealerSet) {
			if(dealer.getDealerId()==id) {
				for (ProductInfoBean prod : dealer.getProduct()) {
					if(prod.getProductName().equals(name))
						return prod.getQuantity();
				}
			}
				
			else 
				return 0;
		}
		return 0;
	}
}
