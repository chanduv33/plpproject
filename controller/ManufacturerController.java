package com.capgemini.storesmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.service.ManufacturerService;
import com.capgemini.storesmanagementsystem.service.ManufacturerServiceImpl;

public class ManufacturerController {
	ManufacturerService manSer = new ManufacturerServiceImpl();
	Scanner sc = new Scanner(System.in);

	public void manufacturer() {
		while (true) {
			System.out.println("Welcome Manufacturer");
			System.out.println("Operation you would like to perform ?");
			System.out.println(" 1. Add Dealer \n " + "2. Set CostPrice \n" + " " + "3. Get Payment Details \n "
					+ "4. Exit as Manufacturer");
			System.out.println("Enter Your Choice");
			System.out.println("===================================================================="
					+ "==========================================================");
			try {
				int manufacturerChoice = sc.nextInt();
				switch (manufacturerChoice) {
				case 1:
					while(true) {
					DealerInfoBean dealer = new DealerInfoBean();
					System.out.println("Enter Dealer Name");
					sc.nextLine();
					dealer.setDealerName(sc.nextLine());
					System.out.println("Enter Dealer Id");
					dealer.setDealerId(sc.nextInt());
					System.out.println("Enter Password for Dealer");
					dealer.setPassword(sc.next());
					if (manSer.addDealer(dealer)) {
						System.out.println("Dealer Added Successfully");
					} else {
						System.out.println("adding dealer has been failed");
					}
					System.out.println("Do u want to add more dealers?Y/N");
					String ch = sc.next();
					if(ch.equals("n") || ch.equals("N"))
					break;
					}
					break;
				case 2:
					System.out.println("Enter Product id");
					ProductInfoBean product = new ProductInfoBean();
					product.setProductId(sc.nextInt());
					System.out.println("Enter new Cost Price");
					product.setCostPrice(sc.nextDouble());
					if(manSer.setCostPrice(product)) {
						System.out.println("Updation Successful");
					} else {
						System.out.println("Updation Unsuccessfull");
					}
					break;
				case 3:
					System.out.println("Enter Order Id to get Payment Details");
					ProductInfoBean bean = manSer.getPaymentDetails(sc.nextInt());
					if(bean!=null) {
					System.out.println(" Order Id " + bean.getOrderId() + " \t ProductName " + bean.getProductName()
							+ " \t Date Of Order " + bean.getDateOfOrder() + " \t Amount " + bean.getAmount()
							+ " \t Date of Delivery " + bean.getDateOfDelivery());
					} else {
						System.out.println("Incorrect Order details");
					}
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
