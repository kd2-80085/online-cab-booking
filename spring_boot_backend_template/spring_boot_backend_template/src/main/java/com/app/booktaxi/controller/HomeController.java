package com.app.booktaxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.AuthDTO;
import com.app.booktaxi.dto.CustomerDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.service.CustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/login")
public class HomeController {
	
	@Autowired
	private CustomerService custService;
	
	@PostMapping
	public CustomerRespDTO  customerLogin(@RequestBody AuthDTO auth) {
		return custService.doLogin(auth);
	}
	
}
