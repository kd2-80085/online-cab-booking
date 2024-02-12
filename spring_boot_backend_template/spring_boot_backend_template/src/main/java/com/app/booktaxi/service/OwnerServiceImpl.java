package com.app.booktaxi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.OwnerDao;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Owner;

@Transactional
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;
	
	@Autowired
	private CarDao carDao;
	
	@Autowired
	private BookingDao bookingDao;

	@Override
	public String updateOwnerStatus(Long ownerId) {

		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not found"));

		if (!(owner.getStatus().equalsIgnoreCase("active"))) {
			owner.setStatus("active");
			Owner updatedOwner = ownerDao.save(owner);
			if (updatedOwner != null)
				return "Owner Approved Successfully " + updatedOwner;
			return null;
		}
		return "Owner is already Approved";

	}

	@Override
	public String deleteOwner(@NotNull Long ownerId) {
		// TODO Auto-generated method stub
		Owner owner=ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not found"));
		
		List<Car> ocars= carDao.findAllByOwner(owner);
		for (Car car : ocars)
		{    
			car.setServiceStatus("inactive");
			carDao.save(car);
		}
		owner.setServiceStatus("inactive");
	   Owner updatedOwner= ownerDao.save(owner);
		if (updatedOwner.getStatus().equalsIgnoreCase("inactive"))
			return "Owner deletion successful";
		return "Owner deletion unsuccessful";
	}

}
