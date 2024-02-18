package com.app.booktaxi.service;

import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.PaymentReqDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.dto.CustomerUpdateProfileDTO;
import com.app.booktaxi.dto.CustomerUpdatePwdDTO;
import com.app.booktaxi.dto.DistanceRespDTO;
import com.app.booktaxi.dto.FeedbackDTO;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.app.booktaxi.dto.CustomerBookingRespDTO;
import com.app.booktaxi.dto.CustomerCarDTO;
import com.app.booktaxi.dto.CustomerPaymentRespDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerRespDTO;

public interface CustomerService {

	CustomerSignupDTO addNewCustomer(CustomerSignupDTO c);

	CustomerRespDTO doLogin( AuthSignInDTO auth);

	List<CustomerBookingRespDTO> getBookingByCustomer(int pageNumber, int pageSize, @NotNull Long customerId);

	FeedbackDTO addNewFeedback(@Valid FeedbackDTO fdto);

	List<CustomerCarDTO> getCars(int pageNumber, int pageSize);
  
	String bookCab(BookingReqDTO bookingReqDto);

	PaymentRespDTO saveNewPayment(@Valid PaymentReqDTO paymentReqDTO);

	String cancelBooking(@NotNull Long bookingid);

	CustomerPaymentRespDTO getPaymentDetails(Long bookingId);

	Object getProfileDetails(Long customerId);

	Object updateProfileDetails(Long customerId, CustomerUpdateProfileDTO custDTO);

	Object updatePassword(Long customerId, CustomerUpdatePwdDTO passDTO);

	List<DistanceRespDTO> getDistanceList();

}
