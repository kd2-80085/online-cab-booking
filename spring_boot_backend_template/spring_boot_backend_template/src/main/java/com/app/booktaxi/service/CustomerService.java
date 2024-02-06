package com.app.booktaxi.service;

import com.app.booktaxi.dto.AuthDTO;
import com.app.booktaxi.dto.CustomerDTO;
import com.app.booktaxi.dto.CustomerRespDTO;

public interface CustomerService {

	CustomerDTO addNewCustomer(CustomerDTO c);

	CustomerRespDTO doLogin(AuthDTO auth);

}
