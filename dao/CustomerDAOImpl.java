package com.capgemini.storesmanagementsystem.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class CustomerDAOImpl implements CustomerDAO {

	private DealerDAOImpl dealerImpl = new DealerDAOImpl();
	@Override
	public boolean buyProduct(DealerInfoBean dealer,CustomerInfoBean customer,String pname) {
		for (CustomerInfoBean cust : CollectionDbClass.customerSet) {
			if(customer.getCustomerId()==cust.getCustomerId()) {
				Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
				while(itr.hasNext()) {
					DealerInfoBean bean = itr.next();
					if(bean.getDealerName().equalsIgnoreCase(dealer.getDealerName()) ){
						//bean.getProduct().getProductName().equalsIgnoreCase(dealer.getProduct().getProductName()))
						
						for (ProductInfoBean  prods : bean.getProduct()) {
							if(prods.getProductName().equals(pname)) {
								cust.setProduct(prods);
								dealerImpl.checkQuantity(bean,pname);
								LocalDate date  = LocalDate.now();
								cust.setDateOfOrder(date);
								cust.setDateOfDelivery(date.plusDays(3));
								cust.setDealer(bean);
								cust.setOrderId(customer.getOrderId());
								cust.setAmount(bean.getSellingPrice());
								System.out.println(cust);
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	@Override
	public CustomerInfoBean login(int id, String password) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		while (itr.hasNext()) {
			CustomerInfoBean bean = itr.next();
			if(bean.getCustomerId()==id && bean.getPassword().equals(password)) {
				return bean;
			}
		}
		return null;
	}
	
	@Override
	public CustomerInfoBean getOrderDetails(int id) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		while(itr.hasNext()) {
			CustomerInfoBean bean = itr.next();
			if(bean.getOrderId()==id) {
				return bean;
			}
		}
		return null;
	}
	
	@Override
	public boolean checkEmailAvailability(String email) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		while(itr.hasNext()) {
			CustomerInfoBean bean = itr.next();
			if(bean.getEmail().equals(email)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
}
