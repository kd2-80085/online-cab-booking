package com.app.booktaxi.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.app.booktaxi.dto.AdminRespDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.dto.PaymentRespDTO;

public interface AdminService {
	List<BookingRespDTO> getTripsByCustomer(int pageNumber, int pageSize, Long customerId);

	List<CarRespDTO> getAllCarsDetails(int pageNumber, int pageSize);

	List<DriverRespDTO> getAllDriversDetails(int pageNumber, int pageSize);

	PaymentRespDTO getPaymentByParticularBooking(Long bookingId, Long paymentId);

	List<FeedbackRespDTO> getDriverFeedback(int pageNumber, int pageSize, @NotNull Long driverId);

	AdminRespDTO doLogin(@Valid AuthSignInDTO auth);

}
