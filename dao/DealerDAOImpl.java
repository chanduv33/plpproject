package com.capgemini.storesmanagementsystem.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class DealerDAOImpl implements DealerDAO {

	@Override
	public boolean placeOrder(OrderDetails order, DealerInfoBean bean) {
				LocalDate date = LocalDate.now();
				order.setDateOfOrder(date);
				order.setStatus("Not yet Delivered");
				order.setDateOfDelivery(date.plusDays(2));
				order.getDealers().add(bean);
				bean.getOrders().add(order);
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
		for (ProductInfoBean prod : dealer.getProduct()) {
			if(prod.getProductId()==id) {
				prod.setSellingPrice(price);
				return true;
			}
		}
		return false;
	}
	 

	@Override
	public int getNumberOfProducts(String name, DealerInfoBean dealer) {
		for (DealerInfoBean bean : CollectionDbClass.dealerSet) {
			if (dealer.getDealerName().equalsIgnoreCase(bean.getDealerName())) {
				for (ProductInfoBean prod : bean.getProduct()) {
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
	public OrderDetails getPaymentDeatils(int oid,DealerInfoBean dealer) {
		for (OrderDetails order : dealer.getOrders()) {
			if(order.getOrderId()==oid) {
				return order;
			}
		}
		return null;
	}

	@Override
	public boolean checkIdAvailability(int id) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		if(itr.hasNext()) {
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerId()==id) {
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
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		if(itr.hasNext()) {
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerName().equalsIgnoreCase(name)) {
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
	public boolean setDeliveredDate(String date,int id,DealerInfoBean dealer) {
		
		for (OrderDetails order : dealer.getOrders()) {
			if(order.getOrderId()==id) {
				Period p1 = Period.between(order.getDateOfOrder(), order.getDateOfDelivery());
				LocalDate deliveredDate = LocalDate.parse(date);
				if(deliveredDate.isBefore(order.getDateOfOrder())) {
					return false;
				} else {
				Period p2 = Period.between(deliveredDate, order.getDateOfDelivery());
				if(p2.getDays()<=p1.getDays()) {
					order.setStatus("Delivered");
					return true;
				} else {
					order.setStatus("Order Delivered Lately");
					return true;
				}
				}
			}
		}
		
		
		return false;
	}
}
