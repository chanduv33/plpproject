package com.capgemini.storesmanagementsystem.dao;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Iterator;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class CustomerDAOImpl implements CustomerDAO {

	private DealerDAOImpl dealerImpl = new DealerDAOImpl();
	
	static boolean  flag = false;
	
	@Override
	public boolean buyProduct(DealerInfoBean dealer, OrderDetails corder, CustomerInfoBean customer, String pname) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerName().equalsIgnoreCase(dealer.getDealerName())) {
				for (ProductInfoBean prods : bean.getProduct()) {
					if (prods.getProductName().equals(pname)) {
						corder.setProductName(prods.getProductName());
						LocalDate date = LocalDate.now();
						corder.setDateOfOrder(date);
						corder.setStatus("Not yet Delivered");
						corder.setDateOfDelivery(date.plusDays(3));
						corder.setAmount(prods.getSellingPrice());
						customer.getOrders().add(corder);

						int quantity = prods.getQuantity() - 1;
						prods.setQuantity(quantity);
						if (prods.getQuantity() <= bean.getMinimumQuantity()) {
							flag=true;
						}

						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public CustomerInfoBean login(int id, String password) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		while (itr.hasNext()) {
			CustomerInfoBean bean = itr.next();
			if (bean.getCustomerId() == id && bean.getPassword().equals(password)) {
				return bean;
			}
		}
		return null;
	}

	@Override
	public OrderDetails getOrderDetails(int id, CustomerInfoBean customer) {
		for (OrderDetails order : customer.getOrders()) {
			if (order.getOrderId() == id) {
				return order;
			}
		}
		return null;
	}

	@Override
	public boolean checkEmailAvailability(String email) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		if (itr.hasNext()) {
			while (itr.hasNext()) {
				CustomerInfoBean bean = itr.next();
				if (bean.getEmail().equals(email)) {
					return false;
				} else {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean checkIdAvailability(int id) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		if (itr.hasNext()) {
			while (itr.hasNext()) {
				CustomerInfoBean bean = itr.next();
				if (bean.getCustomerId() == id) {
					return false;
				} else {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void autoBuy(DealerInfoBean dealer,String name) {
		
		if(flag) {
			for (DealerInfoBean dealers : CollectionDbClass.dealerSet) {
				if(dealer.getDealerName().equalsIgnoreCase(dealers.getDealerName())) {
					for (ProductInfoBean prods : dealers.getProduct()) {
						if(prods.getProductName().equalsIgnoreCase(name)) {
							OrderDetails order = new OrderDetails();
							for (OrderDetails orders : dealers.getOrders()) {
								if(orders.getProductName().equalsIgnoreCase(name)) {
									
									prods.setQuantity(prods.getQuantity() * 2);
									int newOid = orders.getOrderId() + 1;
									order.setOrderId(newOid);
									order.setAmount((prods.getCostPrice()*prods.getQuantity()));
									order.setStatus("Not yet Delivered");
									order.setProductName(prods.getProductName());
									LocalDate newDate = LocalDate.now();
									order.setDateOfOrder(newDate);
									order.setDateOfDelivery(LocalDate.now().plusDays(2));
									
									
								}
							}
							order.getDealers().add(dealers);
							dealers.getOrders().add(order);
							flag=false;
						}
					}
					
				}
			}
		}
		
	}

	@Override
	public boolean setDeliveredDate(String date,int id,CustomerInfoBean customer) {
		for (OrderDetails order : customer.getOrders()) {
			if(order.getOrderId()==id) {
				Period p1 = Period.between(order.getDateOfOrder(), order.getDateOfDelivery());
				LocalDate deliveredDate = LocalDate.parse((CharSequence)date);
				if(deliveredDate.isBefore(order.getDateOfOrder())) {
					return false;
				} else {
				Period p2 = Period.between(deliveredDate, order.getDateOfDelivery());
				if(p2.getDays()<=p1.getDays()) {
					order.setStatus("Delivered");
					return true;
				} else {
					order.setStatus("Order Delivered Lately");
					return true;
				}
				}
			}
		}
		return false;
	}

}
