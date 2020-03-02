package com.capgemini.storesmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.service.AdminService;
import com.capgemini.storesmanagementsystem.service.AdminServiceImpl;
import com.capgemini.storesmanagementsystem.validation.Validations;

public class AdminController {
	Scanner sc = new Scanner(System.in);
	AdminService adminSer = new AdminServiceImpl();
	Validations val = new Validations();
	boolean adminFlag=true;
	public void admin() {
		System.out.println("Welcome Admin");
		
		while (adminFlag) {
			System.out.println("Operation you would like to perform ?");
			System.out.println(" 1. Add Manufacturer \n " + "2. Update Manufacturer Details \n "
					+ "3. Get Manufacturer Details \n " + "4. Get All Manufacturers Details \n "
					+ "5. Exit as Admin");
			System.out.println("Enter Your Choice");
			System.out.println("===================================================================="
					+ "==========================================================");
			try {
				int adminChoice = sc.nextInt();
				switch (adminChoice) {
				case 1:
					ManufacturerInfoBean manufacturer = new ManufacturerInfoBean();
					System.out.println("Enter Manufacturer Name");
					sc.nextLine();
					manufacturer.setManufacturerName(sc.nextLine());
					System.out.println("Enter Password for Manufacturer");
					String password = sc.next();
					manufacturer.setPassword(password);
					if(val.passwordValidtion(password)) {
					System.out.println("Add Product");
					
					ProductInfoBean product = new ProductInfoBean();
					System.out.println("Enter Product Id");
					try {
					product.setProductId(sc.nextInt());
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
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
					System.out.println("Enter Manufacturer Id");
					try {
					manufacturer.setManufacturerId(sc.nextInt());
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
							break;
						}
					}
					manufacturer.setProduct(product);
					product.setManufacturer(manufacturer);

					if (adminSer.addManufacturer(manufacturer)) {
						System.out.println("Manufacturer Added Successfully");
					} else {
						System.out.println("Adding Manufacturer Failed");
					}
					} else {
						System.out.println("Password Must contain more than four letters");
					}
					break;
				case 3:
					System.out.println("Enter Manufacturer Id");
					
					int manId;
					try {
					manId= sc.nextInt();
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
							break;
						}
					}
					ManufacturerInfoBean man = adminSer.getManufacturerDetails(manId);
					if(man!=null) {
					System.out.println("===================================================================="
								+ "==========================================================");
					System.out.println(" ManufacturerName = " + man.getManufacturerName() + " \t Product = "
							+ man.getProduct().getProductName() + " \t Description = "
							+ man.getDescription() + " \t Product Cost = " + man.getProductCost());
					} else {
						System.out.println("Manufacturer Not found");
					}
					break;
				case 2:
					ManufacturerInfoBean bean = new ManufacturerInfoBean();
					System.out.println("Enter Id to Update");
					try {
					bean.setManufacturerId(sc.nextInt());
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
							break;
						}
					}
					System.out.println("Enter New Name for Manufacturer");
					bean.setManufacturerName(sc.next());
					System.out.println("Enter new Password");
					bean.setPassword(sc.next());
					if (adminSer.updateManufacturerDetails(bean) != null) {
						System.out.println("Updated Successfully");
					} else {
						System.out.println("Updation Failed");
					}

					break;
				case 4:
					System.out.println("===================================================================="
							+ "==========================================================");
					List<ManufacturerInfoBean> manufacturers = adminSer.getAllManufacturersDetails();
					if(manufacturers!=null) {
					Iterator<ManufacturerInfoBean> itr = manufacturers.iterator();
					while (itr.hasNext()) {
						ManufacturerInfoBean mans = itr.next();
						System.out.println(" ManufacturerName = " + mans.getManufacturerName()
								+ " \t || Product = " + mans.getProduct().getProductName()
								+ " \t || Description = " + mans.getDescription() + " \t || Product Cost = "
								+ mans.getProductCost()+" \n MId = "+mans.getManufacturerId());
					}
					System.out.println("===================================================================="
							+ "==========================================================");
					} else {
						System.out.println("There are no manufacturers");
					}
					break;
				case 5: adminFlag=false;
					break;
				}
			} catch (InputMismatchException e) {
				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.out.println(exp.getMessage());
				}

			}
			if( adminFlag==false) 
				break;
		}
	}
}
