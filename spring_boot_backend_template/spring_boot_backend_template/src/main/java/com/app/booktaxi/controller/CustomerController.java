package com.app.booktaxi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	public CustomerController() {
		System.out.println("in customer controller");
	}
	
	@Autowired
	private CustomerService custService;
	
	// URL : http://localhost:8080/customer/signup/
				// Method : POST
				// req params : in Body
	            //                (fname,lname,email,password,mobile) 
				// resp :  (id,fname,lname,email,mobile)
	@PostMapping("/signup")
	public ResponseEntity<?> addCustomer(@RequestBody @Valid CustomerSignupDTO c) {
		System.out.println(c);
		System.out.println("in add new customer");
//		return custService.addNewCustomer(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(custService.addNewCustomer(c));
	}
	
	
	
	
	
	
}
