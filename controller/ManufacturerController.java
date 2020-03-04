package com.capgemini.storesmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.exception.IdAlreadyExistsException;
import com.capgemini.storesmanagementsystem.service.DealerService;
import com.capgemini.storesmanagementsystem.service.DealerServiceImpl;
import com.capgemini.storesmanagementsystem.service.ManufacturerService;
import com.capgemini.storesmanagementsystem.service.ManufacturerServiceImpl;
import com.capgemini.storesmanagementsystem.validation.Validations;

public class ManufacturerController {
	ManufacturerService manSer = new ManufacturerServiceImpl();
	DealerService dealerSer = new DealerServiceImpl();
	Scanner sc = new Scanner(System.in);
	Validations val = new Validations();
	boolean manufacturerFlag = true;

	public void manufacturer(ManufacturerInfoBean manufacturer) {
		while (true) {
			System.out.println("Welcome Manufacturer");
			System.out.println("Operation you would like to perform ?");
			System.out.println(" 1. Add Product \n 2. Set CostPrice \n"
					+ " 3. Get Payment Details \n 4. Get All Products \n " + "5. Exit as Manufacturer");
			System.out.println("Enter Your Choice");
			System.out.println("===================================================================="
					+ "==========================================================");
			try {
				int manufacturerChoice = sc.nextInt();
				switch (manufacturerChoice) {
				case 1:
					System.out.println("Add Product");
					ProductInfoBean product = new ProductInfoBean();
					System.out.println("Enter Product Id");
					int pid;
					try {
						pid = sc.nextInt();
						if(manSer.checkProductAvailability(pid, manufacturer))
						product.setProductId(pid);
						else {
							try {
								throw new IdAlreadyExistsException();
							} catch(IdAlreadyExistsException exp) {
								System.err.println(exp.getMessage());
								break;
							}
						}
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.err.println(exp.getMessage());
							break;
						}
					}
					System.out.println("Enter Product Cost");
					double productCost = sc.nextDouble();
					product.setCostPrice(productCost);
					manufacturer.setProductCost(productCost);
					System.out.println("Enter Product Name");
					product.setProductName(sc.next());

					System.out.println("Enter Description about Product");
					manufacturer.setDescription(sc.next());
					manufacturer.getProduct().add(product);
					break;
				case 2:
					System.out.println("Enter Product id");
					ProductInfoBean prod = new ProductInfoBean();
					try {
						prod.setProductId(sc.nextInt());
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.err.println(exp.getMessage());
							break;
						}
					}
					System.out.println("Enter new Cost Price");
					prod.setCostPrice(sc.nextDouble());
					if (manSer.setCostPrice(prod, manufacturer)) {
						System.out.println("Updation Successful");
					} else {
						System.out.println("Updation Unsuccessfull");
					}
					break;
				case 3:
					System.out.println("Enter Order Id to get Payment Details");
					int oid;
					try {
						oid = sc.nextInt();
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.err.println(exp.getMessage());
							break;
						}
					}
					System.out.println("Enter Dealer name");
					sc.nextLine();
					String dname = sc.nextLine();
					OrderDetails bean = bean = manSer.getPaymentDetails(oid, dname);

					if (bean != null) {
						System.out.println(" Order Id " + bean.getOrderId() + " \t ProductName " + bean.getProductName()
								+ " \t Date Of Order " + bean.getDateOfOrder() + " \t Amount " + bean.getAmount()
								+ " \t Date of Delivery " + bean.getDateOfDelivery());
					} else {
						System.out.println("Incorrect Order details");
					}
					break;
				case 4:
					List<ProductInfoBean> prods = manSer.getAllProducts(manufacturer);
					for (ProductInfoBean productInfoBean : prods) {
						System.out.println("Product Name " + productInfoBean.getProductName());
					}
					break;
				case 5:
					manufacturerFlag = false;
					break;
				}
			} catch (InputMismatchException e) {
				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.err.println(exp.getMessage());
					StoresManagementApp.start();
				}
			}
			if (manufacturerFlag == false)
				break;
		}
	}
}
