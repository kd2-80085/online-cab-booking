package com.app.booktaxi.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.entity.Car;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	@Autowired
   private	CarDao carDao;
	
	
	@Override
	public String updateCarStatus(Long carId) {
		
		Car car= carDao.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Bookings Not found"));
		car.setStatus("approved");
		Car updatedCar=carDao.save(car);
		if(updatedCar != null)
		      return "Car Approved Successfully "+updatedCar;
		return null;
	}

}
