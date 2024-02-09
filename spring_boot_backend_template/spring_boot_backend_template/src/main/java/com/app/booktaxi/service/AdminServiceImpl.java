package com.app.booktaxi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dao.PaymentDao;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Feedback;
import com.app.booktaxi.entity.Payment;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private CarDao carDao;
	
	@Autowired
	private DriverDao driverDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<CarRespDTO> getAllCarsDetails(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Car> carList = carDao.findAll(pageable).getContent();
		System.out.println("List of cars : " + carList);	
		
		return carList.stream()
	            .map(car -> mapper.map(car, CarRespDTO.class)) 
	            .collect(Collectors.toList());
		//List<CarRespDTO> carDTOList = new ArrayList<>();
	    //for (Car car : carList) {
		//	CarRespDTO carRespDTO = mapper.map(car, CarRespDTO.class);
	    //    carDTOList.add(carRespDTO);
	    //}
		//System.out.println("Final DTO car list : "+ carDTOList);
		//return carDTOList; 
	}

	@Override
	public List<DriverRespDTO> getAllDriversDetails(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Driver> driverList = driverDao.findAll(pageable).getContent();
		System.out.println("List of drivers : "+driverList);
		
		return driverList.stream()
				.map(driver -> mapper.map(driver, DriverRespDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public PaymentRespDTO getPaymentByParticularBooking(Long bookingId, Long paymentId) {
	    Optional<Payment> paymentDetails = paymentDao.findById(paymentId);

	    if (paymentDetails.isPresent()) {
	        Payment payment = paymentDetails.get();
	        PaymentRespDTO paymentDto = mapper.map(payment, PaymentRespDTO.class);
	        paymentDto.setBookingId(bookingId);
	        return paymentDto;
	    } else {
	        // Handle the case when payment with the given paymentId is not found
	        // For example, you can throw an exception or return a default DTO
	        return new PaymentRespDTO(); // Return a default DTO or handle it accordingly
	    }
	}

	@Override
	public FeedbackRespDTO getDriverFeedback(@NotNull Long driverId) {
		Optional<Feedback> feedbackDetails = feedbackDao.findById(driverId);

	    if (feedbackDetails.isPresent()) {
	    	Feedback feedback = feedbackDetails.get();
	    	FeedbackRespDTO feedbackDto = mapper.map(feedback, FeedbackRespDTO.class);
	    	feedbackDto.setDriverId(driverId);
	        return feedbackDto;
	    } else {
	        return new FeedbackRespDTO(); // Return a default DTO or handle it accordingly
	    }
	}

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
