package com.app.booktaxi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.AdminDao;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dao.PaymentDao;
import com.app.booktaxi.dao.UserEntityDao;
import com.app.booktaxi.dto.AdminRespDTO;
import com.app.booktaxi.dto.AdminSignupDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.dto.OwnerSignupDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.entity.Admin;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Feedback;
import com.app.booktaxi.entity.Owner;
import com.app.booktaxi.entity.Payment;
import com.app.booktaxi.entity.UserEntity;
import com.app.booktaxi.entity.UserRole;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

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
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserEntityDao userEntityDao;
	
	@Override
	public AdminRespDTO doLogin(@Valid AuthSignInDTO auth) {
		Admin admin = adminDao.findByEmail(auth.getEmail()).orElseThrow(() ->
		new ResourceNotFoundException("Invalid Email or Password")
		); 
		System.out.println(admin);
		if (encoder.matches(auth.getPassword(),admin.getPassword()))  {
			return mapper.map(admin, AdminRespDTO.class);
		}
		return null;
	}

	@Override
	public List<CarRespDTO> getAllCarsDetails(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Car> carList = carDao.findAll(pageable).getContent();
		System.out.println("List of cars : " + carList);

		return carList.stream().map(car -> mapper.map(car, CarRespDTO.class)).collect(Collectors.toList());
		// List<CarRespDTO> carDTOList = new ArrayList<>();
		// for (Car car : carList) {
		// CarRespDTO carRespDTO = mapper.map(car, CarRespDTO.class);
		// carDTOList.add(carRespDTO);
		// }
		// System.out.println("Final DTO car list : "+ carDTOList);
		// return carDTOList;
	}

	@Override
	public List<DriverRespDTO> getAllDriversDetails(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Driver> driverList = driverDao.findAll(pageable).getContent();
		System.out.println("List of drivers : " + driverList);

		return driverList.stream().map(driver -> mapper.map(driver, DriverRespDTO.class)).collect(Collectors.toList());
	}

	@Override
	public PaymentRespDTO getPaymentByParticularBooking(Long bookingId, Long paymentId) {
		Payment paymentDetails = paymentDao.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment details Not found for payment id"));
		PaymentRespDTO paymentDto = mapper.map(paymentDetails, PaymentRespDTO.class);
		paymentDto.setBookingId(bookingId);
		return paymentDto;
	}
	    //keep it as it is
//	    Payment paymentDetails = paymentDao.findById(paymentId).orElseThrow(() -> 
//		new ResourceNotFoundException("Payment Not Found")
//			);
//		if(paymentDetails==null) {
//			PaymentRespDTO paymentDto = mapper.map(paymentDetails, PaymentRespDTO.class);
//			return paymentDto;
//		}
//		else {
//			return new PaymentRespDTO();
//		}
	

	@Override
	public List<FeedbackRespDTO> getDriverFeedback(int pageNumber, int pageSize, Long driverId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Driver driver = driverDao.findById(driverId)
				.orElseThrow(() -> new ResourceNotFoundException("Driver Not Found"));
		
		List<Feedback> feedbackList = feedbackDao.findAllByDriver(driver, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("Feedbacks Not Found"));
		
		List<FeedbackRespDTO> feedbackRespDTOList = feedbackList.stream().map(feedback -> {
			FeedbackRespDTO feedbackRespDto = mapper.map(feedback, FeedbackRespDTO.class);
			feedbackRespDto.setDriverId(driverId);
			feedbackRespDto.setFirstName(driver.getFirstName());
			feedbackRespDto.setLastName(driver.getLastName());
			return feedbackRespDto;
		}).collect(Collectors.toList());
		
		return feedbackRespDTOList;
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
			// bookDto.setPaymentId(booking.getPayment().getId());

			return bookDto;
		}).collect(Collectors.toList());

		return bookingRespDTOList;
	}

	@Override
	public List<BookingRespDTO> getAllBookings(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Booking> bookings = bookingDao.findAll(pageable);
		
		System.out.println("List of Bookings : "+ bookings);
		
		List<BookingRespDTO> bookingRespDtoList = bookings.stream().map(booking -> {
			BookingRespDTO bookRespDto = mapper.map(booking, BookingRespDTO.class);

			bookRespDto.setPickUpLocation(booking.getPickupLocation());
			bookRespDto.setCarId(booking.getCar().getId());
			bookRespDto.setCustomerId(booking.getCustomer().getId());
			bookRespDto.setDriverId(booking.getDriver().getId());
			if(booking.getTrip() != null)
				bookRespDto.setTripId(booking.getTrip().getId());
			// bookDto.setPaymentId(booking.getPayment().getId());

			return bookRespDto;
		}).collect(Collectors.toList());
		return bookingRespDtoList;
	}
		
	public AdminSignupDTO addNewAdmin(@Valid AdminSignupDTO adminDto) {
		System.out.println(adminDto);

		Admin admin = mapper.map(adminDto, Admin.class);
		admin.setPassword(encoder.encode(admin.getPassword()));

		UserEntity user = mapper.map(admin, UserEntity.class);
		user.setRole(UserRole.ROLE_ADMIN);
		userEntityDao.save(user);

		return mapper.map(adminDao.save(admin), AdminSignupDTO.class);
	}

}
