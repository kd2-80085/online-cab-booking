package com.app.booktaxi.service;

import java.util.List;

import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.DriverUpdateProfileDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;

public interface DriverService {

	DriverRespDTO doLogin( AuthSignInDTO auth);
	
	String updateDriverStatus( Long driverId);

	String deleteDriver( Long driverId);

	List<CarRespDTO> getAllCarsDetailsByDriver(int pageNumber, int pageSize, Long driverId);

	List<BookingRespDTO> getIncomingBookingsForDriver(int pageNumber, int pageSize, Long driverId);

	List<FeedbackRespDTO> getFeedbacksForDriver(int pageNumber, int pageSize, Long driverId);

	DriverRespDTO updateProfileDetails(Long driverId, DriverUpdateProfileDTO driverDto);	


}
