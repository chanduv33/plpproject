package com.capgemini.storesmanagementsystem.db;

import java.util.HashSet;


import com.capgemini.storesmanagementsystem.dto.AdminInfoBean;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class CollectionDbClass {
	public static final HashSet<AdminInfoBean> adminSet = new HashSet<AdminInfoBean>();
	public static final HashSet<ManufacturerInfoBean> manufacturerSet = new HashSet<ManufacturerInfoBean>();
	public static final HashSet<DealerInfoBean> dealerSet = new HashSet<DealerInfoBean>();
	public static final HashSet<ProductInfoBean> productSet = new HashSet<ProductInfoBean>();
	public static final HashSet<CustomerInfoBean> customerSet = new HashSet<CustomerInfoBean>();
}
