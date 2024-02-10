package com.app.booktaxi.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.entity.Driver;

@Transactional
@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverDao driverDao;
	
	@Override
	public String updateDriverStatus(@NotNull Long driverId) {
		Driver driver = driverDao.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver Not found"));
		if(!(driver.getStatus().equalsIgnoreCase("approved"))) 
		{
		driver.setStatus("approved");
		Driver updatedDriver = driverDao.save(driver);
		if (updatedDriver != null)
			return "Driver Approved Successfully " + updatedDriver;
		}
		return "Driver is already Approved";
	}

	@Override
	public String deleteDriver(@NotNull Long driverId) {
		driverDao.deleteById(driverId);
		if(driverDao.findById(driverId) != null)
			return "Driver deletion unsuccessful";
		return "Driver deletion successful";
	}

}
