package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface DealerService {
	public boolean placeOrder(ProductInfoBean product,DealerInfoBean dealer,ManufacturerInfoBean manufacturer);
	public boolean setSellingPrice(DealerInfoBean dealer,int id,double price);
	public List<ProductInfoBean> getAllProducts(int id);
	public int getNumberOfProducts(String name,int id);
	public DealerInfoBean login(String name, String password);
	public boolean register(DealerInfoBean bean);
	public ProductInfoBean getPaymentDeatils(int oid,DealerInfoBean dealer);
}
