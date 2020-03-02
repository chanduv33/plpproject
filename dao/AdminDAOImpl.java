package com.capgemini.storesmanagementsystem.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public boolean addManufacturer(ManufacturerInfoBean manufacturer) {
		CollectionDbClass.manufacturerSet.add(manufacturer);
		return true;
	}

	@Override
	public ManufacturerInfoBean updateManufacturerDetails(ManufacturerInfoBean manufacturer) {

		Iterator<ManufacturerInfoBean> itr = CollectionDbClass.manufacturerSet.iterator();
		while (itr.hasNext()) {
			ManufacturerInfoBean found = itr.next();
			if (found.getManufacturerId() == manufacturer.getManufacturerId()) {
				found.setPassword(manufacturer.getPassword());
				found.setManufacturerName(manufacturer.getManufacturerName());
				return found;
			}
		}
		return null;
	}

	@Override
	public ManufacturerInfoBean getManufacturerDetails(int id) {
		for (ManufacturerInfoBean man : CollectionDbClass.manufacturerSet) {
			if (man.getManufacturerId() == id) {
				return man;
			}
		}
		return null;
	}

	@Override
	public List<ManufacturerInfoBean> getAllManufacturersDetails() {
		return new ArrayList<ManufacturerInfoBean>(CollectionDbClass.manufacturerSet);
	}

}
