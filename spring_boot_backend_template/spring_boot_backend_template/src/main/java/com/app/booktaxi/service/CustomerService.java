package com.app.booktaxi.service;

import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.PaymentReqDTO;
import com.app.booktaxi.dto.PaymentRespDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerRespDTO;

public interface CustomerService {

	CustomerSignupDTO addNewCustomer(CustomerSignupDTO c);

	CustomerRespDTO doLogin(CustomerSigninDTO auth);

	String bookCab(BookingReqDTO bookingReqDto);

	PaymentRespDTO saveNewPayment(@Valid PaymentReqDTO paymentReqDTO);

	String cancelBooking(@NotNull Long bookingid);

}
