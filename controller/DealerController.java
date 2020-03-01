package com.capgemini.storesmanagementsystem.controller;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.service.DealerService;
import com.capgemini.storesmanagementsystem.service.DealerServiceImpl;
import com.capgemini.storesmanagementsystem.service.ManufacturerService;
import com.capgemini.storesmanagementsystem.service.ManufacturerServiceImpl;

public class DealerController {
	DealerService dealerSer = new DealerServiceImpl();
	ManufacturerService manSer = new ManufacturerServiceImpl();
	Scanner sc = new Scanner(System.in);
	public void dealer() {
		while(true) {
		System.out.println("Welcome Dealer");
		System.out.println("Operation you would like to perform ?");
		System.out.println(" 1. Place Order \n" 
				+ " 2. Set Selling Price \n" 
				+ " 3. Get Payment Details \\n"
				+ " 4. Get All Products \n"
				+ " 5. Get NumberofProducts \n"
				+ " 6. Exit");
		System.out.println("Enter Your Choice");
		System.out.println("===================================================================="
				+ "==========================================================");
		try {
			int dealerChoice = sc.nextInt();
			switch (dealerChoice) {
			case 1:
				while(true) {
				System.out.println("Enter Product");
				String productName = sc.next();
				System.out.println("Enter Manufacturer Name");
				String manName = sc.next();
				System.out.println("Enter Quantity");
				int quantity = sc.nextInt();
				System.out.println("Enter Dealer Id");
				int id = sc.nextInt();
				Iterator<ManufacturerInfoBean> itr = CollectionDbClass.manufacturerSet.iterator();
				while (itr.hasNext()) {
					ManufacturerInfoBean manBean = itr.next();
					if (manBean.getManufacturerName().equalsIgnoreCase(manName)
							&& manBean.getProduct().getProductName().equalsIgnoreCase(productName)) {
						System.out.println("Enter Order Id");
						manBean.getProduct().setOrderId(sc.nextInt());
						dealerSer.placeOrder(manBean.getProduct(), quantity, id);
					} else {
						System.out.println("Placing order has been failed");
					}
				}
				System.out.println("Do u want to place more orders?Y/N");
				String ch = sc.next();
				if(ch.equals("n") || ch.equals("N"))
				break;
				}
				break;
			case 2:
				DealerInfoBean dealer = new DealerInfoBean();
				System.out.println("Enter Selling Price");
				dealer.setSellingPrice(sc.nextDouble());
				System.out.println("Enter Dealer Id");
				dealer.setDealerId(sc.nextInt());
				System.out.println("Enter Product Id");
				int pid	= sc.nextInt();
				if(dealerSer.setSellingPrice(dealer,pid)) {
					System.out.println("Price has been set Successfully");
				} else {
					System.out.println("No products are there to set price");
				}
				break;
			case 3 :
				System.out.println("Enter Product Id");
				int oid = sc.nextInt();
				manSer.getPaymentDetails(oid);
				break;
			case 4 : System.out.println("Enter your id");
			int id = sc.nextInt();
			List<ProductInfoBean> prods = dealerSer.getAllProducts(id);
			if(prods!=null) {
			for (ProductInfoBean productInfoBean : prods) {
				System.out.println("Product "+productInfoBean.getProductName());
			}
			} else {
				System.out.println("Products not found");
			}
			break;
			case 5 : System.out.println("Enter your id");
			 	int did = sc.nextInt();
			 	System.out.println("Enter product Name");
			 	sc.nextLine();
			 	String name = sc.nextLine();
			 	System.out.println("Product Count is "+dealerSer.getNumberOfProducts(name, did));
			case 6 : break;
			default : System.out.println("Enter valid choice");
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
