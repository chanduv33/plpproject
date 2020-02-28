package com.capgemini.storesmanagementsystem.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class DealerDAOImpl implements DealerDAO{
	
	
	@Override
	public boolean placeOrder(ProductInfoBean product, int quantity,int id) {
		
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while(itr.hasNext()) {
			DealerInfoBean bean  = itr.next();
			if(bean.getDealerId()==id) {
				bean.setQuantity(quantity);
				LocalDate date  =  LocalDate.now();
				product.setDateOfOrder(date);
				product.setDateOfDelivery(LocalDate.now().plusDays(2));
				product.setDealer(bean);
				bean.setProduct(Arrays.asList(product));
				product.setAmount(quantity*product.getCostPrice());
				System.out.println(bean.getProduct());
				return true;
			}	
		}
		return false;
	}

	@Override
	public boolean setSellingPrice(DealerInfoBean dealer,int id) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while(itr.hasNext()) {
			DealerInfoBean bean  = itr.next();
			if(bean.getDealerId()==dealer.getDealerId()) {
				for (ProductInfoBean prod : bean.getProduct()) {
					if(prod.getProductId()==id) {
						prod.getDealer().setSellingPrice(dealer.getSellingPrice());
					}
				}
			}	
		}
		return false;
	}

	public void checkQuantity(DealerInfoBean dealer, int pid) {
		int quantity = dealer.getQuantity()-1;
		dealer.setQuantity(quantity);
		if(dealer.getQuantity()<=dealer.getMinimumQuantity()) {
			for (ProductInfoBean prods : dealer.getProduct()) {
				if(prods.getProductId()==pid) {
					autoBuyStocks(prods, dealer.getQuantity()*2);
				}
			}
			
		}
	}

	
	public void autoBuyStocks(ProductInfoBean product, int quantity) {
		int newOid =product.getOrderId()+1;
		placeOrder(product, quantity, newOid);
	}
}
