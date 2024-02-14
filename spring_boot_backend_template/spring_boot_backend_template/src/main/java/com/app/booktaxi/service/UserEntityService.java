package com.app.booktaxi.service;

import com.app.booktaxi.dto.*;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Owner;

public interface UserEntityService {
//add a method for user signup
	Signup registerUser(Signup request);
	CustomerSignupDTO registerCustomer(CustomerSignupDTO request);
	DriverSignupDTO registerDriver(DriverSignupDTO request);
	OwnerSignupDTO registerOwner(OwnerSignupDTO request);
	
}
