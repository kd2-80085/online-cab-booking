package com.app.booktaxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.AuthDTO;
import com.app.booktaxi.service.CustomerService;


@RestController
@RequestMapping("/login")
public class HomeController {
	
	@Autowired
	private CustomerService custService;
	
	
	
	
	
	
	
	@PostMapping
	public ResponseEntity<?>  customerLogin(@RequestBody AuthDTO auth) {
		
		
		
		
		
		return ResponseEntity.ok(custService.doLogin(auth));
	}
	
}
