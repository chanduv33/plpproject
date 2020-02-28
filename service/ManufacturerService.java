package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface ManufacturerService {
	public boolean addDealer(DealerInfoBean dealer);
	public boolean setCostPrice(ProductInfoBean product);
	public ProductInfoBean getPaymentDetails(int orderId);
}
