package com.capgemini.storesmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class CustomerInfoBean {
	private int customerId;
	private int orderId;
	private DealerInfoBean dealer;
	private ProductInfoBean product;
	private LocalDate dateOfOrder;
	private LocalDate dateOfDelivery;
	private double amount;
}
