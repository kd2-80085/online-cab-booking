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
import com.app.booktaxi.entity.Customer;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {


	@Autowired
	private BookingDao bookingDao;
	
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
	public List<BookingRespDTO> getTripsByCustomer(int pageNumber, int pageSize,Long customerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		// fetches the Page of Emps --> getContent() --> List<Emp>
		Customer cust=new Customer();
		cust.setId(customerId);
		
		List<Booking> bookingList = bookingDao.findAllByCustomer(cust)
				.orElseThrow(()-> new ResourceNotFoundException("Bookings Not found"));
		
		System.out.println(bookingList);
//		List<Booking> bookingList = bookingDao.findByCustomer(customerId,pageable)
//				.orElseThrow(()-> new ResourceNotFoundException("Bookings Not found"));
//		return bookingList.stream().map(booking -> mapper.map(booking, BookingRespDTO.class))
//				.collect(Collectors.toList());
		return null;
		
	}

}
