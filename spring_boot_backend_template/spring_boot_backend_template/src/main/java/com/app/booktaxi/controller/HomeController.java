package com.app.booktaxi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.AdminRespDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerRespDTO;
import com.app.booktaxi.service.AdminService;
import com.app.booktaxi.service.CustomerService;
import com.app.booktaxi.service.DriverService;
import com.app.booktaxi.service.OwnerService;


@RestController
@RequestMapping("/home/login")
public class HomeController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping
	public ResponseEntity<?>authenticateLogin(@RequestBody @Valid AuthSignInDTO auth) {

		if(auth.getRole().equalsIgnoreCase("admin")) {
			AdminRespDTO adminDto = adminService.doLogin(auth);
			if(adminDto != null)
				return ResponseEntity.ok(adminDto);
			else 
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
		}	
		else if(auth.getRole().equalsIgnoreCase("customer")) {
			CustomerRespDTO custDto = custService.doLogin(auth);
			if(custDto != null)
				return ResponseEntity.ok(custDto);
			else 
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
		}
		else if(auth.getRole().equalsIgnoreCase("owner")) {
			OwnerRespDTO ownerDto = ownerService.doLogin(auth);
			if(ownerDto != null)
				return ResponseEntity.ok(ownerDto);
			else 
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
		}
		else if(auth.getRole().equalsIgnoreCase("driver")) {
			DriverRespDTO driverDto = driverService.doLogin(auth);
			if(driverDto != null)
				return ResponseEntity.ok(driverDto);
			else 
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
		}
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
	}
	
	
	
	
	
	
}
