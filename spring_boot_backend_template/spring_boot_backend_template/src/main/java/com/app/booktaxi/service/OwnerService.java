package com.app.booktaxi.service;

import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.CarUpdateDTO;
import com.app.booktaxi.dto.DriverSignupDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerCarRespDTO;
import com.app.booktaxi.dto.OwnerRespDTO;
import com.app.booktaxi.dto.OwnerSignupDTO;
import com.app.booktaxi.dto.OwnerUpdateProfileDTO;
import com.app.booktaxi.dto.OwnerUpdatePwdDTO;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface OwnerService {

	OwnerSignupDTO addNewOwner( OwnerSignupDTO ownerDto);
	
	OwnerRespDTO doLogin( AuthSignInDTO auth);
	
	String updateOwnerStatus( Long ownerId);
  
	String deleteOwner(@NotNull Long ownerId);
  
	DriverRespDTO addDriverDetails(DriverSignupDTO newDriver,Long ownerId);

	CarRespDTO addCarDetails(AddCarDTO newCar, Long ownerId);

	List<OwnerCarRespDTO> getAllCars(int pageNumber, int pageSize, Long ownerId);

	List<DriverRespDTO> getAllDrivers(int pageNumber, int pageSize, Long ownerId);

	Object updateCarDetails(Long carId, CarUpdateDTO carDTO);

	Object updateProfileDetails(Long ownerId, OwnerUpdateProfileDTO ownerDto);

	Object updatePassword(Long ownerId, OwnerUpdatePwdDTO passDTO);

	Object getProfileDetails(Long ownerId);

}
