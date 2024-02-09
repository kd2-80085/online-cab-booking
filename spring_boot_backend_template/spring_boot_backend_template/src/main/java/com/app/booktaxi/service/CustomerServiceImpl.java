package com.app.booktaxi.service;

import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Encoder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.entity.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao custDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public CustomerSignupDTO addNewCustomer(CustomerSignupDTO c) {
		System.out.println(c);
		Customer customer = mapper.map(c, Customer.class);
		customer.setPassword(encoder.encode(customer.getPassword()));
		return mapper.map(custDao.save(customer), CustomerSignupDTO.class);
	}

	@Override
	public CustomerRespDTO doLogin(CustomerSigninDTO auth) {
		Customer customer = custDao.getByEmail(auth.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password"));
		System.out.println(customer);
		if (encoder.matches(auth.getPassword(), customer.getPassword())) {
			System.out.println("inside if block");
			return mapper.map(customer, CustomerRespDTO.class);
		} else
			return null;
	}

}
