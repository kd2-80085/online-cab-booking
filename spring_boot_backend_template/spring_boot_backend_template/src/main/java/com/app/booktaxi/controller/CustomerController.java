package com.app.booktaxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.CustomerDTO;
import com.app.booktaxi.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	public CustomerController() {
		System.out.println("in customer controller");
	}
	
	@Autowired
	private CustomerService custService;
	
	@PostMapping("/add")
	public CustomerDTO addCustomer(@RequestBody CustomerDTO c) {
		System.out.println(c);
		System.out.println("in add new customer");
		return custService.addNewCustomer(c);
	}
}
