package com.app.booktaxi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerCarRespDTO;
import com.app.booktaxi.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	public OwnerService ownerService;
	
	// add Driver
		// URL : http://localhost:8080/owner/addDriver
		// Method : post
		// resp : successful driverRespDTO or exc
	@PostMapping("/addDriver")
	public DriverRespDTO addDriverDetails(@RequestBody @Valid AddDriverDTO dto) {
		System.out.println("in add driver Owner Controller" + dto);
		return ownerService.addDriverDetails(dto);
	}
	
	// add car
		// URL : http://localhost:8080/owner/addCar
		// Method : post
		// resp : successful carRespDTO or exc
	@PostMapping("/addCar/{ownerId}")
	public CarRespDTO addCarDetails(@RequestBody @Valid AddCarDTO dto, @PathVariable @NotNull Long ownerId) {
		System.out.println("in add car Owner Controller" + dto);
		return ownerService.addCarDetails(dto, ownerId);
	}
	
	// view all cars
			// URL : http://localhost:8080/owner/cars/{ownerId}
			// Method : GET
            // request : in Head -> (ownerId)	
			// resp : successful carRespDTO or exc
	@GetMapping("/cars/{ownerId}")
	public ResponseEntity<?> getCars(@RequestParam(defaultValue = "0",required = false)int pageNumber,
			                         @RequestParam(defaultValue = "3",required = false)int pageSize,
			                         @PathVariable Long ownerId)
	{
	  System.out.println("in getCars "+ownerId);
	  List<OwnerCarRespDTO> carList = ownerService.getAllCars(pageNumber,pageSize,ownerId);
	  if(carList.isEmpty())
		  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	  else
		  return new ResponseEntity<>(carList, HttpStatus.OK);
		
	}
	
	// view all drivers
				// URL : http://localhost:8080/owner/drivers/{ownerId}
				// Method : GET
	            // request : in Head -> (ownerId)	
				// resp : successful DriverRespDTO or exc
	@GetMapping("/drivers/{ownerId}")
	public ResponseEntity<?> getDrivers(@RequestParam(defaultValue = "0",required = false)int pageNumber,
			                         @RequestParam(defaultValue = "3",required = false)int pageSize,
			                         @PathVariable Long ownerId)
	{
		System.out.println("in getDrivers "+ownerId);
		List<DriverRespDTO> driverList = ownerService.getAllDrivers(pageNumber,pageSize,ownerId);
		  if(driverList.isEmpty())
			  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		  else
			  return new ResponseEntity<>(driverList, HttpStatus.OK);
	}
	
}
