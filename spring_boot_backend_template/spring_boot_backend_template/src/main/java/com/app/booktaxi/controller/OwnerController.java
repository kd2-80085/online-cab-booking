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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.CarUpdateDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.CustomerUpdateProfileDTO;
import com.app.booktaxi.dto.CustomerUpdatePwdDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerCarRespDTO;
import com.app.booktaxi.dto.OwnerSignupDTO;
import com.app.booktaxi.dto.OwnerUpdateProfileDTO;
import com.app.booktaxi.dto.OwnerUpdatePwdDTO;
import com.app.booktaxi.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	public OwnerService ownerService;

	// add owner
	// URL : http://localhost:8080/owner/signup/
	// Method : POST
	// req params : in Body
	// (fname,lname,email,password,mobile,isDriver,serviceStatus)
	// resp : (id,fname,lname,email,mobile,isDriver,serviceStatus)
	@PostMapping("/signup")
	public ResponseEntity<?> addOwner(@RequestBody @Valid OwnerSignupDTO ownerDto) {
		System.out.println(ownerDto);
		System.out.println("in add new owner");
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.addNewOwner(ownerDto));
	}

	// add driver
	// URL : http://localhost:8080/owner/addDriver
	// Method : post
	// resp : successful driverRespDTO or exc
	@PostMapping("/addDriver")
	public ResponseEntity<?> addDriverDetails(@RequestBody @Valid AddDriverDTO dto) {
		System.out.println("in add driver Owner Controller" + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.addDriverDetails(dto));
	}

	// add car
	// URL : http://localhost:8080/owner/addCar/{ownerId}
	// Method : post
	// resp : successful carRespDTO or exc
	@PostMapping("/addCar/{ownerId}")
	public ResponseEntity<?> addCarDetails(@RequestBody @Valid AddCarDTO dto, @PathVariable @NotNull Long ownerId) {
		System.out.println("in add car Owner Controller" + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.addCarDetails(dto, ownerId));
	}

	// view all cars
	// URL : http://localhost:8080/owner/cars/{ownerId}
	// Method : GET
	// request : in Head -> (ownerId)
	// resp : successful carRespDTO or exc
	@GetMapping("/cars/{ownerId}")
	public ResponseEntity<?> getCars(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize, @PathVariable Long ownerId) {
		System.out.println("in getCars " + ownerId);
		List<OwnerCarRespDTO> carList = ownerService.getAllCars(pageNumber, pageSize, ownerId);
		if (carList.isEmpty())
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
	public ResponseEntity<?> getDrivers(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize, @PathVariable Long ownerId) {
		System.out.println("in getDrivers " + ownerId);
		List<DriverRespDTO> driverList = ownerService.getAllDrivers(pageNumber, pageSize, ownerId);
		if (driverList.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		else
			return new ResponseEntity<>(driverList, HttpStatus.OK);
	}

	// URL : http://localhost:8080/owner/car/edit/{carId}
	// Method : PUT
	// req params : in Head - (carId)
	// in Body - (Company,Location,Model,SeatingCapacity)
	// resp : successful CarRespDTO or exc
	@PutMapping("/owner/car/edit/{carId}")
	public ResponseEntity<?> updateCar(@PathVariable Long carId, @RequestBody @Valid CarUpdateDTO carDTO) {
		System.out.println("In update Car : " + carId + " " + carDTO);
		return ResponseEntity.status(HttpStatus.OK).body(ownerService.updateCarDetails(carId, carDTO));
	}

	// update profile
	// URL : http://localhost:8080/owner/profile/{ownerId}
	// Method : PUT
	// req params : in Head - (ownerId)
	// in Body - (fname,lname,email,mobile)
	// resp : (id,fname,lname,email,mobile)
	@PutMapping("/profile/{ownerId}")
	public ResponseEntity<?> updateProfile(@PathVariable Long ownerId, @RequestBody OwnerUpdateProfileDTO ownerDto) {
		System.out.println("In updateProfile : " + ownerId + " " + ownerDto);
		return ResponseEntity.status(HttpStatus.OK).body(ownerService.updateProfileDetails(ownerId, ownerDto));
	}

	// URL : http://localhost:8080/owner/password/{ownerId}
	// Method : PUT
	// req params : in Head - (ownerId)
	// in Body - (oldPassword, newPassword)
	// resp : (id,fname,lname,email,mobile)
	@PutMapping("/password/{ownerId}")
	public ResponseEntity<?> updatePassword(@PathVariable Long ownerId, @RequestBody OwnerUpdatePwdDTO passDTO) {
		System.out.println("In updatePassword : " + ownerId + " " + passDTO);
		return ResponseEntity.status(HttpStatus.OK).body(ownerService.updatePassword(ownerId, passDTO));
	}
}
