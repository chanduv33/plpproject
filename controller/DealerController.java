package com.capgemini.storesmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.service.DealerService;
import com.capgemini.storesmanagementsystem.service.DealerServiceImpl;

public class DealerController {
	DealerService dealerSer = new DealerServiceImpl();
	Scanner sc = new Scanner(System.in);
	boolean dealerFlag = true;

	public void dealer(DealerInfoBean dealer) {
		while (dealerFlag) {
			System.out.println("Welcome Dealer");
			System.out.println("Operation you would like to perform ?");
			System.out.println(" 1. Place Order \n" + " 2. Set Selling Price \n" + " 3. Get Payment Details \n"
					+ " 4. Get All Products \n" + " 5. Get All Orders \n 6. Get NumberofProducts \n" + " 7. Is OrderDelivered \n 8. Exit");
			System.out.println("Enter Your Choice");
			System.out.println("===================================================================="
					+ "==========================================================");
			try {
				int dealerChoice = sc.nextInt();
				switch (dealerChoice) {
				case 1:
					System.out.println("Enter Product");
					String productName = sc.next();
					System.out.println("Enter Manufacturer Name");
					String manName = sc.next();
					System.out.println("Enter Quantity");
					int quantity;
					try {
						quantity = sc.nextInt();
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.err.println(exp.getMessage());
							break;
						}
					}
					Iterator<ManufacturerInfoBean> itr = CollectionDbClass.manufacturerSet.iterator();
					while (itr.hasNext()) {
						ManufacturerInfoBean manBean = itr.next();
						
						if (manBean.getManufacturerName().equalsIgnoreCase(manName)) {
							for (ProductInfoBean prod : manBean.getProduct()) {
								if (prod.getProductName().equalsIgnoreCase(productName)) {
									OrderDetails order = new OrderDetails();
									System.out.println("Enter Order Id");
									try {
										ProductInfoBean product = new ProductInfoBean();
										int oid = sc.nextInt();
										order.setOrderId(oid);
										order.setProductName(prod.getProductName());
										product.setQuantity(quantity);
										product.setSellingPrice(prod.getCostPrice() + 50);
										order.setAmount(prod.getCostPrice()*quantity);
										product.setProductName(prod.getProductName());
										product.setProductId(prod.getProductId());
										dealer.getProduct().add(product);
										
									} catch (InputMismatchException e) {
										try {
											throw new EnterValidInputException();
										} catch (EnterValidInputException exp) {
											System.err.println(exp.getMessage());
											break;
										}
									}
									if (dealerSer.placeOrder(order, dealer)) {
										System.out.println("Order Placed successfully");

									} else {
										System.out.println("Placing order has been failed");

									}
								} 
							} 
						} 
					}
					break;
				case 2:
					System.out.println("Enter Selling Price");
					double newPrice = sc.nextDouble();
					System.out.println("Enter Product Id");
					int pid;
					try {
						pid = sc.nextInt();
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.err.println(exp.getMessage());
							break;
						}
					}
					if (dealerSer.setSellingPrice(dealer, pid,newPrice)) {
						System.out.println("Price has been set Successfully");
					} else {
						System.out.println("No products are there to set price");
					}
					break;
				case 3:
					System.out.println("Enter Order Id");
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
					OrderDetails bean = dealerSer.getPaymentDeatils(oid,dealer);
					if (bean != null) {
						
								System.out.println(" Order Id " + bean.getOrderId() + " \t ProductName " +
									bean.getProductName()
								+ " \t Date Of Order " + bean.getDateOfOrder()+" \t Date of Delivery" + bean.getDateOfDelivery()+
								" \t Amount " + bean.getAmount()+" \t Status = "+bean.getStatus());
							
						
					} else {
						System.out.println("Incorrect Order details");
					}
					break;
				case 4:
					List<ProductInfoBean> prods = dealer.getProduct();
					if (prods != null) {
						for (ProductInfoBean productInfoBean : prods) {
							System.out.println("Product " + productInfoBean.getProductName()+" \t Selling Price : "+productInfoBean.getSellingPrice());
						}
					} else {
						System.out.println("Products not found");
					}
					break;
				case 5 : for (OrderDetails beanorder : dealer.getOrders()) {
					System.out.println(" Order Id " + beanorder.getOrderId() + " \t ProductName " +
							beanorder.getProductName()
						+ " \t Date Of Order " + beanorder.getDateOfOrder() + " \t Amount " + beanorder.getAmount()+
						" \t Status "+beanorder.getStatus());
				}
					break;
				case 6:
					System.out.println("Enter product Name");
					sc.nextLine();
					String name = sc.nextLine();
					int count = dealerSer.getNumberOfProducts(name, dealer);
					if(count!=0) {
					System.out.println("Product Name "+name+" \t Product Count is " + count);
					} else {
						System.out.println("Product Not found");
					}
					break;
				case 7:System.out.println("Enter Delivered Date");
				String date = sc.next();
				System.out.println("Enter Order Id");
				int orid = sc.nextInt();
				if(dealerSer.setDeliveredDate(date, orid, dealer)) {
					System.out.println("Order Delivered Successfully");
				} else {
					System.out.println("Order has not yet delivered");
				}
					break;
					
				case 8:
					dealerFlag = false;
					break;
				default:
					System.out.println("Enter valid choice");
				}

			} catch (InputMismatchException e) {
				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.err.println(exp.getMessage());
					StoresManagementApp.start();
				}
			}
			if (dealerFlag == false)
				break;
		}
	}
}
