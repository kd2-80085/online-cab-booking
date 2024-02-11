package com.app.booktaxi.service;

import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerCarRespDTO;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface OwnerService {

	
	String updateOwnerStatus( Long ownerId);

	DriverRespDTO addDriverDetails(AddDriverDTO newDriver);

	CarRespDTO addCarDetails(AddCarDTO newCar, Long ownerId);

	List<OwnerCarRespDTO> getAllCars(int pageNumber, int pageSize, Long ownerId);

	List<DriverRespDTO> getAllDrivers(int pageNumber, int pageSize, Long ownerId);

}
