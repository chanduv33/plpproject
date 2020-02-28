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
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while(itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if(bean.getDealerName().equalsIgnoreCase(dealer.getDealerName()) ){
				//bean.getProduct().getProductName().equalsIgnoreCase(dealer.getProduct().getProductName()))
				
				for (ProductInfoBean  prods : bean.getProduct()) {
					if(prods.getProductName().equals(pname)) {
						customer.setProduct(prods);
						dealerImpl.checkQuantity(bean,prods.getProductId());
						LocalDate date  = LocalDate.now();
						customer.setDateOfOrder(date);
						customer.setDateOfDelivery(date.plusDays(3));
						customer.setDealer(bean);
						customer.setAmount(bean.getSellingPrice());
						System.out.println(customer);
						CollectionDbClass.customerSet.add(customer);
						return true;
					}
				}
			}
		}
		return false;
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

}
