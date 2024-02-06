package com.app.booktaxi.service;

import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dto.AuthDTO;
import com.app.booktaxi.dto.CustomerDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.entity.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public CustomerDTO addNewCustomer(CustomerDTO c) {
		System.out.println(c);
		Customer customer = custDao.save(mapper.map(c, Customer.class));
		System.out.println(customer.toString());
		return mapper.map(customer, CustomerDTO.class);
	}


	@Override
	public CustomerRespDTO doLogin(AuthDTO auth) {
		Customer customer = custDao.findByEmailAndPassword
				(auth.getEmail(), auth.getPassword())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Email or Password"));
				
				
		return mapper.map(customer, CustomerRespDTO.class);
	}
	
}
