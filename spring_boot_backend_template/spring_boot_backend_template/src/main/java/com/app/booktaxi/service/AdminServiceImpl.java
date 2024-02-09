package com.app.booktaxi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ModelMapper mapper;

//	@Override
//	public List<BookingRespDTO> getTripsByCustomer(int pageNumber, int pageSize) {
//		
//		// Creates a PageRequest(imple class of Pageable : i/f for pagination) based
//		// upon page no n size
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		// fetches the Page of Emps --> getContent() --> List<Emp>
//		List<Employee> empList = empDao.findAll(pageable).getContent();
//		return empList.stream().map(emp -> mapper.map(emp, EmpRespDTO.class)).collect(Collectors.toList());
//	}

	@Override
	public List<BookingRespDTO> getTripsByCustomer(int pageNumber, int pageSize, Long customerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not found"));

		List<Booking> bookingList = bookingDao.findByCustomer(customer, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("Bookings Not found"));

		List<BookingRespDTO> bookingRespDTOList = bookingList.stream().map(booking -> {
			BookingRespDTO bookDto = mapper.map(booking, BookingRespDTO.class);

			bookDto.setCarId(booking.getCar().getId());
			bookDto.setCustomerId(booking.getCustomer().getId());
			bookDto.setDriverId(booking.getDriver().getId());
			bookDto.setTripId(booking.getTrip().getId());
			//bookDto.setPaymentId(booking.getPayment().getId());

			return bookDto;
		}).collect(Collectors.toList());

		return bookingRespDTOList;
	}

}
