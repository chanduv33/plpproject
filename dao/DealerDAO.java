package com.capgemini.storesmanagementsystem.dao;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface DealerDAO {
	public boolean placeOrder(ProductInfoBean product, int quantity,int id);
	public boolean setSellingPrice(DealerInfoBean dealer,int id);
	public List<ProductInfoBean> getAllProducts(int id);
	public int getNumberOfProducts(String name,int id);
	public DealerInfoBean login(String name, String password);
}
