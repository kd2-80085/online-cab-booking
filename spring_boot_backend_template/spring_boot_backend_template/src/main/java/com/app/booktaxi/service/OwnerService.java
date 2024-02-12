package com.app.booktaxi.service;

import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerCarRespDTO;
import com.app.booktaxi.dto.OwnerRespDTO;
import com.app.booktaxi.dto.OwnerSignupDTO;

import java.util.List;

import javax.validation.Valid;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface OwnerService {

	OwnerSignupDTO addNewOwner( OwnerSignupDTO ownerDto);
	
	OwnerRespDTO doLogin( AuthSignInDTO auth);
	
	String updateOwnerStatus( Long ownerId);

	DriverRespDTO addDriverDetails(AddDriverDTO newDriver);

	CarRespDTO addCarDetails(AddCarDTO newCar, Long ownerId);

	List<OwnerCarRespDTO> getAllCars(int pageNumber, int pageSize, Long ownerId);

	List<DriverRespDTO> getAllDrivers(int pageNumber, int pageSize, Long ownerId);

}
