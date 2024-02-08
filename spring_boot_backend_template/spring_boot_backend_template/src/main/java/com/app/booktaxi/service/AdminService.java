package com.app.booktaxi.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.app.booktaxi.dto.BookingRespDTO;

public interface AdminService {

	

	List<BookingRespDTO> getTripsByCustomer(int pageNumber, int pageSize, Long customerId);

}
