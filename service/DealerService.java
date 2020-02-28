package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface DealerService {
	public boolean placeOrder(ProductInfoBean product, int quantity,int id);
	public boolean setSellingPrice(DealerInfoBean dealer,int id);
}
