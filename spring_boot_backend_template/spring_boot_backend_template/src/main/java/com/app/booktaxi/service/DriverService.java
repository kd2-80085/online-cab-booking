package com.app.booktaxi.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;

public interface DriverService {



	List<CarRespDTO> getAllCarsDetailsByDriver(int pageNumber, int pageSize, Long driverId);

	List<BookingRespDTO> getIncomingBookingsForDriver(int pageNumber, int pageSize, @NotNull Long driverId);

	List<FeedbackRespDTO> getFeedbacksForDriver(int pageNumber, int pageSize, @NotNull Long driverId);



}
