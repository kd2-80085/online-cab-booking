package com.app.booktaxi.service;

import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.CustomerRespDTO;

public interface CustomerService {

	CustomerSignupDTO addNewCustomer(CustomerSignupDTO c);

	CustomerRespDTO doLogin(CustomerSigninDTO auth);

}
