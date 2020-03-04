package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface DealerService {
	public boolean placeOrder(OrderDetails order,DealerInfoBean dealer);
	public boolean setSellingPrice(DealerInfoBean dealer,int id,double price);
	public int getNumberOfProducts(String name,DealerInfoBean dealer);
	public DealerInfoBean login(String name, String password);
	public boolean register(DealerInfoBean bean);
	public OrderDetails getPaymentDeatils(int oid,DealerInfoBean dealer);
	public boolean checkIdAvailability(int id);
	public boolean checkNameAvailability(String name) ;
	public boolean setDeliveredDate(String date,int id,DealerInfoBean dealer);
}
