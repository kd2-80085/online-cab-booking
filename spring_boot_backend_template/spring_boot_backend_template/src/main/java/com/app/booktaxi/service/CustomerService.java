package com.app.booktaxi.service;

import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.dto.FeedbackDTO;
import com.app.booktaxi.entity.Car;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.app.booktaxi.dto.CustomerBookingRespDTO;
import com.app.booktaxi.dto.CustomerCarDTO;
import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerRespDTO;

public interface CustomerService {

	CustomerSignupDTO addNewCustomer(CustomerSignupDTO c);

	CustomerRespDTO doLogin(CustomerSigninDTO auth);

	List<CustomerBookingRespDTO> getBookingByCustomer(int pageNumber, int pageSize, @NotNull Long customerId);

	FeedbackDTO addNewFeedback(@Valid FeedbackDTO fdto);

	List<CustomerCarDTO> getCarsByLocation(int pageNumber, int pageSize, String location);
  
	String bookCab(BookingReqDTO bookingReqDto);


}
