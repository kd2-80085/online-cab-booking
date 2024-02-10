package com.app.booktaxi.service;

import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.websocket.Encoder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao custDao;
	@Autowired
	private CarDao carDao;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public CustomerSignupDTO addNewCustomer(CustomerSignupDTO c) {
		System.out.println(c);
		Customer customer = mapper.map(c, Customer.class);
		customer.setPassword(encoder.encode(customer.getPassword()));
		return mapper.map(custDao.save(customer), CustomerSignupDTO.class);
	}

	@Override
	public CustomerRespDTO doLogin(CustomerSigninDTO auth) {
		Customer customer = custDao.getByEmail(auth.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password"));
		System.out.println(customer);
		if (encoder.matches(auth.getPassword(), customer.getPassword())) {
			System.out.println("inside if block");
			return mapper.map(customer, CustomerRespDTO.class);
		} else
			return null;
	}

	@Override
	public String bookCab(BookingReqDTO bookingReqDto) {

		Car car = carDao.findById(bookingReqDto.getCarId())
				.orElseThrow(() -> new ResourceNotFoundException("Car not found"));
		Driver driver = driverDao.findById(bookingReqDto.getDriverId())
				.orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
		Customer customer = custDao.findById(bookingReqDto.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

		Booking newBooking = mapper.map(bookingReqDto, Booking.class);
		newBooking.setCar(car);
		newBooking.setDriver(driver);
		newBooking.setCustomer(customer);
		
		Booking savedBooking = bookingDao.save(newBooking);
		if (savedBooking != null)
			return "Car booked Successfully " + savedBooking;

		return "Sorry Car booking unsuccessfull";
	}

}
