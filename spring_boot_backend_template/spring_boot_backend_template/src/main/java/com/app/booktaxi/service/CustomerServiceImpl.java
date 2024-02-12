package com.app.booktaxi.service;

import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
import com.app.booktaxi.dao.PaymentDao;
import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.PaymentReqDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Payment;

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
	private PaymentDao paymentDao;

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

	@Override
	public PaymentRespDTO saveNewPayment(PaymentReqDTO paymentReqDTO)
	{
		System.out.println(paymentReqDTO);
		Payment payment = mapper.map(paymentReqDTO, Payment.class);
		System.out.println(payment);
		Long id=paymentReqDTO.getBookingId();
//		System.out.println(
//				bookingDao.findById(id));
		Booking booking=bookingDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));		
		System.out.println(booking);
		payment.setBooking(booking);
		Payment savedPayment= paymentDao.save(payment);
		PaymentRespDTO paymentRespDTO=mapper.map(savedPayment, PaymentRespDTO.class);
		if (paymentRespDTO != null)
			return  paymentRespDTO;
		return null;
	}

	@Override
	public String cancelBooking(@NotNull Long bookingid) 
	{
		//System.out.println(bookingDao.findById(bookingid));
		Booking booking=bookingDao.findById(bookingid).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));		
      System.out.println(booking);
				booking.setBookingStatus("cancelled");
		Booking cancelledBooking = bookingDao.save(booking);
		if (cancelledBooking != null)
			return "Booking Cancelled Successfully " + cancelledBooking;
		return "Booking Cant be Cancelled";
	}

}
