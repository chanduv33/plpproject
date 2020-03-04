package com.capgemini.storesmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.exception.IdAlreadyExistsException;
import com.capgemini.storesmanagementsystem.exception.NameAlreadyExistsException;
import com.capgemini.storesmanagementsystem.service.AdminService;
import com.capgemini.storesmanagementsystem.service.AdminServiceImpl;
import com.capgemini.storesmanagementsystem.service.ManufacturerService;
import com.capgemini.storesmanagementsystem.service.ManufacturerServiceImpl;
import com.capgemini.storesmanagementsystem.validation.Validations;

public class AdminController {
	Scanner sc = new Scanner(System.in);
	AdminService adminSer = new AdminServiceImpl();
	ManufacturerService manSer = new ManufacturerServiceImpl();
	Validations val = new Validations();
	boolean adminFlag = true;

	public void admin() {
		System.out.println("Welcome Admin");

		while (adminFlag) {
			System.out.println("Operation you would like to perform ?");
			System.out.println(" 1. Add Manufacturer \n " + "2. Update Manufacturer Details \n "
					+ "3. Get Manufacturer Details \n " + "4. Get All Manufacturers Details \n " + "5. Exit as Admin");
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
					String name = sc.nextLine();
					if(manSer.checkNameAvailability(name)) {
					if (val.nameValidation(name)) {
						manufacturer.setManufacturerName(name);
						System.out.println("Enter Password for Manufacturer");
						String password = sc.next();
						manufacturer.setPassword(password);
						if (val.passwordValidtion(password)) {
							System.out.println("Enter Manufacturer Id");
							int manId=sc.nextInt();
							if(manSer.checkIdAvailability(manId)) {
							manufacturer.setManufacturerId(manId);
							if (adminSer.addManufacturer(manufacturer)) {
								System.out.println("Manufacturer Added Successfully");
							} else {
								System.out.println("Adding Manufacturer Failed");
							}
							} else {
								try {
									throw new IdAlreadyExistsException();
								} catch(IdAlreadyExistsException exp) {
									System.err.println(exp.getMessage());
									break;
								}
							}
						} else {
							System.out.println("Password Must contain more than four letters");
						}
					} else {
						System.out.println("Please enter valid Name");
					}
					} else {
						try {
							throw new NameAlreadyExistsException();
						} catch (NameAlreadyExistsException e) {
							System.err.println(e.getMessage());
							break;
						}
					}
					break;
				case 3:
					System.out.println("Enter Manufacturer Id");

					int manId;
					try {
						manId = sc.nextInt();
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.err.println(exp.getMessage());
							break;
						}
					}
					ManufacturerInfoBean man = adminSer.getManufacturerDetails(manId);
					if (man != null) {
						System.out.println("===================================================================="
								+ "==========================================================");
						System.out.println(" ManufacturerName = " + man.getManufacturerName());
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
							System.err.println(exp.getMessage());
							break;
						}
					}
					System.out.println("Enter New Name for Manufacturer");
					sc.nextLine();
					String newName = sc.nextLine();
					if(manSer.checkNameAvailability(newName)) {
					if (val.nameValidation(newName)) {
						bean.setManufacturerName(newName);
						System.out.println("Enter new Password");
						bean.setPassword(sc.next());
						if (adminSer.updateManufacturerDetails(bean) != null) {
							System.out.println("Updated Successfully");
						} else {
							System.out.println("Updation Failed");
						}
					} else {
						System.out.println("Enter valid name");
					}
					} else {
						try {
							throw new NameAlreadyExistsException();
						} catch (NameAlreadyExistsException e) {
							System.err.println(e.getMessage());
						}
					}
					break;
				case 4:
					System.out.println("===================================================================="
							+ "==========================================================");
					List<ManufacturerInfoBean> manufacturers = adminSer.getAllManufacturersDetails();
					if (manufacturers != null) {
						Iterator<ManufacturerInfoBean> itr = manufacturers.iterator();
						while (itr.hasNext()) {
							ManufacturerInfoBean mans = itr.next();
							System.out.println(" ManufacturerName = " + mans.getManufacturerName() 
									+ " \t MId = " + mans.getManufacturerId());
						}
						System.out.println("===================================================================="
								+ "==========================================================");
					} else {
						System.out.println("There are no manufacturers");
					}
					break;
				case 5:
					adminFlag = false;
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
			if (adminFlag == false)
				break;
		}
	}
}
