package com.capgemini.storesmanagementsystem.dao;

import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface CustomerDAO {
	public boolean buyProduct(DealerInfoBean dealer,OrderDetails orders,CustomerInfoBean customer,String pname);
	public OrderDetails getOrderDetails(int id,CustomerInfoBean customer);
	public CustomerInfoBean login(int id, String password);
	public boolean checkEmailAvailability(String email);
	public boolean checkIdAvailability(int id);
	public void autoBuy(DealerInfoBean bean,String name);
	public boolean setDeliveredDate(String date,int id,CustomerInfoBean customer);
}
