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

	public void customer() {
		while (true) {
			System.out.println("Welcome Customer");
			System.out.println("Available Choices are..");
			System.out.println(" 1. Buy Product \n " + "2. Get Order Dealits \n " + "3. Exit");
			System.out.println("Enter Your Choice");
			System.out.println("========================================================");
			try {
				int customerChoice = sc.nextInt();
				switch (customerChoice) {

				case 1:
					while(true) {
					Set<DealerInfoBean> prods = CollectionDbClass.dealerSet;
					Iterator<DealerInfoBean> itr = prods.iterator();
					System.out.println("Available Products are.");
					while (itr.hasNext()) {
						DealerInfoBean bean = itr.next();
						for (ProductInfoBean product : bean.getProduct()) {
							System.out.println("Dealer Name : "+bean.getDealerName()+" \t Product Name : "+product.getProductName());
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
					customer.setCustomerId(sc.nextInt());
					System.out.println("Enter Order Id");
					customer.setOrderId(sc.nextInt());
					cusSer.buyProduct(dealer, customer,pname);
					System.out.println("Do u want to place more orders?Y/N");
					String ch = sc.next();
					if(ch.equals("n") || ch.equals("N"))
					break;
					}
					break;
				case 2:
					System.out.println("Enter Order Id to get Payment Details");
					int oid = sc.nextInt();
					CustomerInfoBean bean = cusSer.getOrderDetails(oid);
					System.out.println(
							" Order Id " + bean.getOrderId() + " \t ProductName " + bean.getProduct().getProductName()
									+ " \t Date Of Order " + bean.getDateOfOrder() + " \t Amount " + bean.getAmount());
					break;
				case 3:
					break;
				}
			} catch (InputMismatchException e) {
				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.out.println(exp.getMessage());
				}
			}
			break;
		}
	}
}