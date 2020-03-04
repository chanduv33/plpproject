package com.capgemini.storesmanagementsystem.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.capgemini.storesmanagementsystem.dao.AdminDAO;
import com.capgemini.storesmanagementsystem.dao.AdminDAOImpl;
import com.capgemini.storesmanagementsystem.dao.DealerDAO;
import com.capgemini.storesmanagementsystem.dao.DealerDAOImpl;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAO;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAOImpl;
import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EmailAlreadyExistsException;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.exception.IdAlreadyExistsException;
import com.capgemini.storesmanagementsystem.exception.NameAlreadyExistsException;
import com.capgemini.storesmanagementsystem.service.AdminService;
import com.capgemini.storesmanagementsystem.service.AdminServiceImpl;
import com.capgemini.storesmanagementsystem.service.CustomerService;
import com.capgemini.storesmanagementsystem.service.CustomerServiceImpl;
import com.capgemini.storesmanagementsystem.service.DealerService;
import com.capgemini.storesmanagementsystem.service.DealerServiceImpl;
import com.capgemini.storesmanagementsystem.service.ManufacturerService;
import com.capgemini.storesmanagementsystem.service.ManufacturerServiceImpl;
import com.capgemini.storesmanagementsystem.validation.Validations;

public class StoresManagementApp {
	public static void start() {

		Scanner sc = new Scanner(System.in);
		AdminService adminSer = new AdminServiceImpl();
		DealerService dealerSer = new DealerServiceImpl();
		ManufacturerService manSer = new ManufacturerServiceImpl();
		CustomerService cusSer = new CustomerServiceImpl();
		String uname;
		String password;
		Validations val = new Validations();
		boolean flag = true;
		while (flag) {

			System.out.println("Who You are..??");
			System.out.println("Available options \n " + "1. Admin \n " + "2. Manufacturer \n " + "3. Dealer \n "
					+ "4. Customer \n " + "5. Exit \n ");
			System.out.println("===================================================================="
					+ "==========================================================");
			System.out.println("Enter Your Choice");
			try {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter UserName");
					sc.nextLine();
					uname = sc.nextLine();
					System.out.println("Enter Password");
					password = sc.nextLine();
					if (uname.equals("Admin") && password.equals("admin@123")) {
						System.out.println("Admin Login Successful");
						AdminController admin = new AdminController();
						admin.admin();
					} else {
						System.out.println("Login Unsuccessful");
					}

					break;
				case 2:
					System.out.println("Enter UserName");
					sc.nextLine();
					uname = sc.nextLine();
					System.out.println("Enter Password");
					password = sc.nextLine();
					if (val.passwordValidtion(password)) {
						ManufacturerInfoBean manufacturer = manSer.login(uname, password);
						if (manSer.login(uname, password) != null) {
							System.out.println("Manufacturer Login Successful");
							ManufacturerController controller = new ManufacturerController();
							controller.manufacturer(manufacturer);
						} else {
							System.out.println("Login Unsuccessful");
						}
					} else {
						System.out.println("Password Must contains more than 4 letters");
					}
					break;
				case 3:
					System.out.println(" 1. Register \n 2. Login \n Enter your choice...");
					try {
						int ch = sc.nextInt();
						if (ch == 1) {
							DealerInfoBean dealer = new DealerInfoBean();
							System.out.println("Enter Name");
							sc.nextLine();
							String dealerName = sc.nextLine();
							if (dealerSer.checkNameAvailability(dealerName)) {
								if (val.nameValidation(dealerName)) {
									dealer.setDealerName(dealerName);
									System.out.println("Enter Dealer Id");
									try {
										int did = sc.nextInt();
										if (dealerSer.checkIdAvailability(did)) {
											dealer.setDealerId(did);
										} else {
											try {
												throw new IdAlreadyExistsException();
											} catch (IdAlreadyExistsException exp) {
												System.err.println(exp.getMessage());
												break;
											}
										}
									} catch (InputMismatchException e) {
										try {
											throw new EnterValidInputException();
										} catch (EnterValidInputException exp) {
											System.out.println(exp.getMessage());
											break;
										}
									}
									System.out.println("Enter Password for Dealer");
									String dpassword = sc.next();
									if (val.passwordValidtion(dpassword)) {
										dealer.setPassword(dpassword);
										if (dealerSer.register(dealer)) {
											System.out.println("Dealer Registered Successfully");
										} else {
											System.out.println("Registration has been failed");
										}
									} else {
										System.out.println("Password must be greater than four letters");
									}
								} else {
									System.out.println("Enter Valid Name");
								}
							} else {
								try {
									throw new NameAlreadyExistsException();
								} catch (NameAlreadyExistsException e) {
									System.err.println(e.getMessage());
								}
							}
						} else if (ch == 2) {
							System.out.println("Enter UserName");
							sc.nextLine();
							uname = sc.nextLine();
							System.out.println("Enter Password");
							password = sc.nextLine();
							if (val.passwordValidtion(password)) {
								DealerInfoBean dealer = dealerSer.login(uname, password);
								if (dealer != null) {
									System.out.println("Dealer Login Successful");
									DealerController controller = new DealerController();
									controller.dealer(dealer);
								} else {
									System.out.println("Login Unsuccessful");
								}
							} else {
								System.out.println("Password Must contains more than 4 letters");
							}
						} else {
							System.out.println("Enter valid choice");
						}
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
						}
					}

					break;
				case 4:
					System.out.println(" 1. Register \n 2. Login \n Enter your choice...");
					try {
						int ch = sc.nextInt();
						if (ch == 1) {
							System.out.println("Enter Customer id");
							int cid;
							try {
								cid = sc.nextInt();
								if (cusSer.checkIdAvailability(cid)) {

									System.out.println("Enter Email");
									String email = sc.next();
									System.out.println("Enter Password");
									String pwd = sc.next();
									if (cusSer.checkEmailAvailability(email)) {
										if (val.emailValidation(email)) {
											if (val.passwordValidtion(pwd)) {
												CustomerInfoBean bean = new CustomerInfoBean();
												bean.setCustomerId(cid);
												bean.setPassword(pwd);
												bean.setEmail(email);
												CollectionDbClass.customerSet.add(bean);
											} else {
												System.err.println("Password Must contains more than 4 letters");
											}
										} else {
											System.err.println("Please Enter Valid Email");
										}
									} else {
										try {
											throw new EmailAlreadyExistsException();
										} catch (EmailAlreadyExistsException e) {
											System.err.println(e.getMessage());
										}
									}
								} else {
									try {
										throw new IdAlreadyExistsException();
									} catch (IdAlreadyExistsException exp) {
										System.err.println(exp.getMessage());
									}
								}
							} catch (InputMismatchException e) {
								try {
									throw new EnterValidInputException();
								} catch (EnterValidInputException exp) {
									System.out.println(exp.getMessage());
									break;
								}
							}
						} else if (ch == 2) {
							System.out.println("Enter Customer id");
							int id;
							try {
								id = sc.nextInt();
							} catch (InputMismatchException e) {
								try {
									throw new EnterValidInputException();
								} catch (EnterValidInputException exp) {
									System.out.println(exp.getMessage());
									break;
								}
							}
							System.out.println("Enter Password");
							password = sc.next();
							if (val.passwordValidtion(password)) {
								CustomerInfoBean customer = cusSer.login(id, password);
								if (customer != null) {
									System.out.println("Csutomer Login Successful");
									CustomerController controller = new CustomerController();
									controller.customer(customer);
								} else {
									System.out.println("Customer Doesnt exists");
								}
							} else {
								System.err.println("Password Must contains more than 4 letters");
							}
						} else {
							System.out.println("Enter valid choice");
						}
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.err.println(exp.getMessage());
						}
					}
					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Invalid Choice");
				}
			} catch (InputMismatchException e) {
				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.err.println(exp.getMessage());
					start();
				}
			}
		}
	}
}
