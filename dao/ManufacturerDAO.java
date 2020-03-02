package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface ManufacturerDAO {
	//public boolean addDealer(DealerInfoBean dealer);
	public boolean setCostPrice(ProductInfoBean product,ManufacturerInfoBean bean);
	public ProductInfoBean getPaymentDetails(int orderId,String name);
	public ManufacturerInfoBean login(String name, String password);
	public boolean addProduct(ManufacturerInfoBean bean);
	public List<ProductInfoBean> getAllProducts(ManufacturerInfoBean bean);
}
