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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dao.UserEntityDao;
import com.app.booktaxi.dao.DistanceDao;
import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dao.PaymentDao;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.CustomerUpdateProfileDTO;
import com.app.booktaxi.dto.CustomerUpdatePwdDTO;
import com.app.booktaxi.dto.FeedbackDTO;
import com.app.booktaxi.dto.PaymentReqDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CustomerBookingRespDTO;
import com.app.booktaxi.dto.CustomerCarDTO;
import com.app.booktaxi.dto.CustomerPaymentRespDTO;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Distance;
import com.app.booktaxi.entity.Feedback;
import com.app.booktaxi.entity.Payment;
import com.app.booktaxi.entity.UserEntity;
import com.app.booktaxi.entity.UserRole;
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
	private PaymentDao paymentDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private FeedbackDao feedDao;

	@Autowired
	private PaymentDao payDao;

	@Autowired
	private DistanceDao distDao;
	
	@Autowired
	private UserEntityDao userEntityDao;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public CustomerSignupDTO addNewCustomer(CustomerSignupDTO c) {
		//System.out.println(c);
		Customer customer = mapper.map(c, Customer.class);
		customer.setPassword(encoder.encode(customer.getPassword()));
		
		UserEntity user=mapper.map(customer, UserEntity.class);
		user.setRole(UserRole.ROLE_CUSTOMER);
		userEntityDao.save(user);
		
		return mapper.map(custDao.save(customer), CustomerSignupDTO.class);
	}

	@Override
	public CustomerRespDTO doLogin(AuthSignInDTO auth) {
		Customer customer = custDao.findByEmail(auth.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password"));
		//System.out.println(customer);

		if (encoder.matches(auth.getPassword(), customer.getPassword())) {
			return mapper.map(customer, CustomerRespDTO.class);
		} else
			return null;
	}

	@Override
	public List<CustomerBookingRespDTO> getBookingByCustomer(int pageNumber, int pageSize, @NotNull Long customerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Customer customer = custDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException(" CustomerId doesn't exist"));

		List<Booking> bookingslist = bookingDao.findByCustomer(customer, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("Bookings not found"));

		List<CustomerBookingRespDTO> custBookingRespList = bookingslist.stream().map(booking -> {
			CustomerBookingRespDTO bookDTO = mapper.map(booking, CustomerBookingRespDTO.class);
			bookDTO.setTripId(booking.getTrip().getId());
			List<Payment> payments = booking.getPayments();
			for (Payment payment : payments) {
				if (payment.getPaymentStatus().equalsIgnoreCase("success")) {
					bookDTO.setPaymentId(payment.getId());
					break;
				}
			}
			return bookDTO;
		}).collect(Collectors.toList());
		return custBookingRespList;
	}

	@Override
	public FeedbackDTO addNewFeedback(@Valid FeedbackDTO fdto) {
		Booking booking = bookingDao.findById(fdto.getBookingId())
				.orElseThrow(() -> new ResourceNotFoundException("BookingId not found"));
		fdto.setDriverId(booking.getDriver().getId());
		Feedback feedback = mapper.map(fdto, Feedback.class);
		return mapper.map(feedDao.save(feedback), FeedbackDTO.class);
	}

	@Override
	public List<CustomerCarDTO> getCarsByLocation(int pageNumber, int pageSize, String location) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		List<Car> carList = carDao.findByLocation(location, pageable);
		List<CustomerCarDTO> customerCarDTOList = carList.stream()
				.filter(car -> car.getStatus().equalsIgnoreCase("available")).map(cars -> {
					System.out.println(" in cars" + cars);
					CustomerCarDTO carDTO = mapper.map(cars, CustomerCarDTO.class);
					carDTO.setDriverName(cars.getDriver().getFirstName().concat(" " + cars.getDriver().getLastName()));
					carDTO.setDriverMobile(cars.getDriver().getMobile());
					return carDTO;
				}).collect(Collectors.toList());
		System.out.println("customerCarDTOList values : " + customerCarDTOList);
		return customerCarDTOList;
	}

	@Override
	public String bookCab(BookingReqDTO bookingReqDto) {

		Car car = carDao.findById(bookingReqDto.getCarId())
				.orElseThrow(() -> new ResourceNotFoundException("Car not found"));
		Driver driver = driverDao.findById(bookingReqDto.getDriverId())
				.orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
		Customer customer = custDao.findById(bookingReqDto.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

		Distance distance = distDao.findByPickupLocationAndDropLocation(bookingReqDto.getPickupLocation(),bookingReqDto.getDropLocation());
		System.out.println("distance values = "+distance);

		Booking newBooking = mapper.map(bookingReqDto, Booking.class);
		newBooking.setCar(car);
		newBooking.setDriver(driver);
		newBooking.setCustomer(customer);
		newBooking.setBookingStatus("pending");
		Booking savedBooking = bookingDao.save(newBooking);
		if (savedBooking != null) {
			BookingReqDTO bookDTO = mapper.map(savedBooking, BookingReqDTO.class);
			bookDTO.setAmount(distance.getDistance() * 20);
			bookDTO.setCarId(car.getId());
			bookDTO.setCustomerId(customer.getId());
			bookDTO.setDriverId(driver.getId());
			System.out.println("bookDTO values = " + bookDTO);
			return "Booking details added Successfully & Payment Pending" + bookDTO;
		}
		return "Sorry adding booking details unsuccessfull";
	}

	@Override
	public CustomerPaymentRespDTO getPaymentDetails(Long bookingId) {
		Booking booking = bookingDao.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("BookingId doesn't exist"));
		Payment payment = payDao.findByBooking(booking);
		// System.out.println("in Payment values = "+payment);
		return mapper.map(payment, CustomerPaymentRespDTO.class);
	}

	@Override
	public Object getProfileDetails(Long customerId) {
		Customer customer = custDao.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("Id not found"));
		System.out.println("customer values = "+customer);

		return mapper.map(customer, CustomerRespDTO.class);
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

	public Object updateProfileDetails(Long customerId, CustomerUpdateProfileDTO custDTO) {
		Customer customer = custDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("CustomerId doesn't exist"));
		System.out.println("Customer values = " + customer);
		customer.setFirstName(custDTO.getFirstName());
		customer.setLastName(custDTO.getLastName());
		customer.setEmail(custDTO.getEmail());
		customer.setMobile(custDTO.getMobile());
		CustomerRespDTO custRespDTO = mapper.map(custDao.save(customer), CustomerRespDTO.class);
		if (custRespDTO != null)
			return "Profile updated Successfully " + custRespDTO;
		return "Profile updation Failed";
	}

	@Override
	public Object updatePassword(Long customerId, CustomerUpdatePwdDTO passDTO) {
		Customer customer = custDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("CustomerId doesn't exist"));
		if (encoder.matches(passDTO.getOldPassword(), customer.getPassword())) {
			customer.setPassword(encoder.encode(passDTO.getNewPassword()));
			CustomerRespDTO custRespDTO = mapper.map(custDao.save(customer), CustomerRespDTO.class);
			if (custRespDTO != null)
				return "Password Updated Successfully " + custRespDTO;
			return "Password Updation Failed";
		}
		return "Invalid Password";
	}
}
