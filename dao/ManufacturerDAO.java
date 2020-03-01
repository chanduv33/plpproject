package com.capgemini.storesmanagementsystem.dao;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface ManufacturerDAO {
	public boolean addDealer(DealerInfoBean dealer);
	public boolean setCostPrice(ProductInfoBean product);
	public ProductInfoBean getPaymentDetails(int orderId);
	public ManufacturerInfoBean login(String name, String password);
}
