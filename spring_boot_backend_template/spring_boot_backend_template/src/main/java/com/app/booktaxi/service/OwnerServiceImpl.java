package com.app.booktaxi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.OwnerDao;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Owner;

public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@Override
	public String updateOwnerStatus(Long ownerId) {

		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not found"));
		owner.setStatus("approved");
		Owner updatedOwner = ownerDao.save(owner);
		if (updatedOwner != null)
			return "Owner Approved Successfully " + updatedOwner;
		return null;

	}

}