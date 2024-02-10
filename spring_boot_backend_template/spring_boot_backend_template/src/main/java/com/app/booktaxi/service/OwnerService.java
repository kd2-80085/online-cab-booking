package com.app.booktaxi.service;

import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;

public interface OwnerService {

	
	String updateOwnerStatus( Long ownerId);

	DriverRespDTO addDriverDetails(AddDriverDTO newDriver);

	CarRespDTO addCarDetails(AddCarDTO newCar, Long ownerId);

}
