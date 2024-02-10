package com.app.booktaxi.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.Encoder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dto.CustomerSigninDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.FeedbackDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CustomerBookingRespDTO;
import com.app.booktaxi.dto.CustomerCarDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Feedback;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao custDao;

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private FeedbackDao feedDao;
	
	@Autowired
	private CarDao carDao;
	
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
	public List<CustomerBookingRespDTO> getBookingByCustomer(int pageNumber, int pageSize, @NotNull Long customerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Customer customer = custDao.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException(" CustomerId doesn't exist"));
		
		List<Booking> bookingslist = bookingDao.findByCustomer(customer, pageable)
				.orElseThrow(()-> new ResourceNotFoundException("Bookings not found"));
		
		List<CustomerBookingRespDTO> custBookingRespList = bookingslist.stream().map(booking -> {
			CustomerBookingRespDTO bookDTO = mapper.map(booking, CustomerBookingRespDTO.class);
			bookDTO.setTripId(booking.getTrip().getId() );
			bookDTO.setPaymentId(booking.getPayment().getId());
			return bookDTO;
		}).collect(Collectors.toList());
		return custBookingRespList;
	}

	@Override
	public FeedbackDTO addNewFeedback(@Valid FeedbackDTO fdto) {
		Booking booking = bookingDao.findById(fdto.getBookingId())
				.orElseThrow(()-> new ResourceNotFoundException("BookingId not found"));
		fdto.setDriverId(booking.getDriver().getId());
		Feedback feedback = mapper.map(fdto, Feedback.class);
		return mapper.map(feedDao.save(feedback) , FeedbackDTO.class);
	}

	@Override
	public List<CustomerCarDTO> getCarsByLocation(int pageNumber, int pageSize, String location) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		List<Car> carList = carDao.findByLocation(location);
		List<CustomerCarDTO> customerCarDTOList = carList.stream()
				.filter(car->car.getStatus().equalsIgnoreCase("available")).map(cars -> {
					System.out.println(" in cars"+cars);
			CustomerCarDTO carDTO = mapper.map(cars, CustomerCarDTO.class);
			carDTO.setDriverName(cars.getDriver().getFirstName().concat(" "+cars.getDriver().getLastName()));
			carDTO.setDriverMobile(cars.getDriver().getMobile());
			return carDTO;
		}).collect(Collectors.toList());
		System.out.println("customerCarDTOList values : "+customerCarDTOList);
		return customerCarDTOList;
	}
		
	

}
