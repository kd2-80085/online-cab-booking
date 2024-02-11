package com.app.booktaxi.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	public OwnerService ownerService;
	
	// add owner
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
}
