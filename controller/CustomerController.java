package com.capgemini.storesmanagementsystem.controller;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.service.CustomerService;
import com.capgemini.storesmanagementsystem.service.CustomerServiceImpl;

public class CustomerController {
	CustomerService cusSer = new CustomerServiceImpl();
	Scanner sc = new Scanner(System.in);
	boolean customerFlag = true;

	public void customer() {
		while (customerFlag) {
			System.out.println("Welcome Customer");
			System.out.println("Available Choices are..");
			System.out.println(" 1. Buy Product \n " + "2. Get Order Dealits \n " + "3. Exit");
			System.out.println("Enter Your Choice");
			System.out.println("===================================================================="
					+ "==========================================================");
			try {
				int customerChoice = sc.nextInt();
				switch (customerChoice) {

				case 1:
					Set<DealerInfoBean> prods = CollectionDbClass.dealerSet;
					Iterator<DealerInfoBean> itr = prods.iterator();
					System.out.println("Available Products are.");
					while (itr.hasNext()) {
						DealerInfoBean bean = itr.next();
						for (ProductInfoBean product : bean.getProduct()) {
							System.out.println("Dealer Name : " + bean.getDealerName() + " \t Product Name : "
									+ product.getProductName());
						}
					}
					System.out.println("Enter Product You Want to Buy");
					DealerInfoBean dealer = new DealerInfoBean();
					sc.nextLine();
					String pname = sc.nextLine();
					System.out.println("Enter Dealer Name");
					dealer.setDealerName(sc.nextLine());
					CustomerInfoBean customer = new CustomerInfoBean();
					System.out.println("Enter Customer Id");
					try {
						customer.setCustomerId(sc.nextInt());
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
							break;
						}
					}
					System.out.println("Enter Order Id");
					try {
						customer.setOrderId(sc.nextInt());
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
							break;
						}
					}
					if (cusSer.buyProduct(dealer, customer, pname)) {
						System.out.println("Order Placed Successfully");
					} else {
						System.out.println("Order Placement has been failed");
					}
					break;
				case 2:
					System.out.println("Enter Order Id to get Payment Details");
					int oid;
					try {
						oid = sc.nextInt();
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
							break;
						}
					}
					CustomerInfoBean bean = cusSer.getOrderDetails(oid);
					if (bean != null) {
						System.out.println(" Order Id " + bean.getOrderId() + " \t ProductName "
								+ bean.getProduct().getProductName() + " \t Date Of Order " + bean.getDateOfOrder()
								+ " \t Amount " + bean.getAmount());
					} else {
						System.out.println("Order not found");
					}
					break;
				case 3:
					customerFlag = false;
					break;
				}
			} catch (InputMismatchException e) {
				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.out.println(exp.getMessage());
				}
			}
			if (customerFlag == false)
				break;
		}
	}
}
