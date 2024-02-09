package com.app.booktaxi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.service.CustomerService;


@RestController
@RequestMapping("/login")
public class HomeController {
	
	@Autowired
	private CustomerService custService;
	
	@PostMapping
	public ResponseEntity<?>customerLogin(@RequestBody @Valid CustomerSigninDTO auth) {
		return ResponseEntity.ok(custService.doLogin(auth));
	}
	
}
