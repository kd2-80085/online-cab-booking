package com.app.booktaxi.service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dao.UserEntityDao;
import com.app.booktaxi.dao.DistanceDao;
import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dao.Payment2Dao;
import com.app.booktaxi.dao.PaymentDao;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.CustomerUpdateProfileDTO;
import com.app.booktaxi.dto.CustomerUpdatePwdDTO;
import com.app.booktaxi.dto.DistanceRespDTO;
import com.app.booktaxi.dto.FeedbackDTO;
import com.app.booktaxi.dto.PaymentReqDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.dto.RazorPayReqDTO;
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
import com.app.booktaxi.entity.Payment2;
import com.app.booktaxi.entity.UserEntity;
import com.app.booktaxi.entity.UserRole;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
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
	private FeedbackDao feedDao;

	@Autowired
	private PaymentDao payDao;

	@Autowired
	private Payment2Dao pay2Dao;
	
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
	public List<CustomerCarDTO> getCars(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Car> carList1 = carDao.findAll( );

		List<CustomerCarDTO> customerCarDTOList = carList1.stream()
				.filter(car -> car.getStatus().equalsIgnoreCase("available")).map(cars -> {
					System.out.println(" in cars" + cars);
					CustomerCarDTO carDTO = mapper.map(cars, CustomerCarDTO.class);
					carDTO.setDriverId(cars.getDriver().getId());
					carDTO.setDriverName(cars.getDriver().getFirstName().concat(" " + cars.getDriver().getLastName()));
					carDTO.setDriverMobile(cars.getDriver().getMobile());
					return carDTO;
				}).collect(Collectors.toList());
		System.out.println("customerCarDTOList values : " + customerCarDTOList);
		return customerCarDTOList;
	}

	@Override
	public BookingReqDTO bookCab(BookingReqDTO bookingReqDto) {

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
		newBooking.setBookingDateTime(LocalDateTime.now());
		Booking savedBooking = bookingDao.save(newBooking);
		if (savedBooking != null) {
			BookingReqDTO bookDTO = mapper.map(savedBooking, BookingReqDTO.class);
			System.out.println("Distance    **** "+ distance.getDistance() );
			bookDTO.setAmount(distance.getDistance() * 20);
			bookDTO.setCarId(car.getId());
			bookDTO.setCustomerId(customer.getId());
			bookDTO.setDriverId(driver.getId());
			System.out.println("before Payment order creation bookDTO values = " + bookDTO);
			Order order =createOrder(bookDTO.getAmount());
	          // Order generated from razorpay {"amount":4000,"amount_paid":0,"notes":[],"created_at":1708259817,"amount_due":4000,"currency":"INR","receipt":"txn_123456","id":"order_NcLRHAECnJgEHv","entity":"order","offer_id":null,"status":"created","attempts":0}
			Payment2 newPayment=new Payment2();
			newPayment.setAmount((bookDTO.getAmount())/100);
			newPayment.setBooking(savedBooking);
			newPayment.setRazor_order_id(order.get("id"));
			newPayment.setPaymentStatus("Pending");
			Payment2 savedPendingPaymentDetails= pay2Dao.save(newPayment);
			System.out.println("Saved pending payment "+savedPendingPaymentDetails);
			bookDTO.setPaymentOrderStaus("created");
			bookDTO.setPaymentOrderId(order.get("id"));

			return bookDTO;
		}
		return null;
	}

		
	public Order createOrder(double amount) {
		 RazorpayClient razorpayClient = null;
		 String key=null;
		 String secret=null;
        try {
            razorpayClient = new RazorpayClient("rzp_test_x3KOSVgdGrp9K5", "LDjufgMbewOXdgaz5WRtF5Mt");
        } catch (RazorpayException e) {
            e.printStackTrace();
        }

        if (razorpayClient != null) {
            JSONObject options = new JSONObject();
            options.put("amount",amount );
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");

            try {
                Order order = razorpayClient.Orders.create(options);
               System.out.println("Order generated from razorpay "+order);
               return order;
            } catch (RazorpayException e) {
                e.printStackTrace();
            }
        }
        return null;
		
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
		booking.addPayments(savedPayment);
		
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

	@Override
	public List<DistanceRespDTO> getDistanceList() {
		List<Distance> distanceList = distDao.findAll();
		System.out.println(distanceList);
		List<DistanceRespDTO> distanceRespDtoList = distanceList.stream().
				map(distance -> {
						DistanceRespDTO distRespDto = mapper.map(distance, DistanceRespDTO.class);
						System.out.println(distRespDto);
						return distRespDto;
					}
				).collect(Collectors.toList()); 
		return distanceRespDtoList;
	}

	@Override
	public String saveRazorPayPayment(@Valid RazorPayReqDTO razorPayReqDTO) {
		Payment2 initialPayment= pay2Dao.findByRazorOrderId(razorPayReqDTO.getRazorpay_order_id());
		if(initialPayment != null)
		{
			Booking booking= bookingDao.findById(initialPayment.getBooking().getId()).orElseThrow(() -> new ResourceNotFoundException("Booking doesn't exist"));
			booking.setBookingStatus("success");
			bookingDao.save(booking);
			//payment is successful
			initialPayment.setPaymentDateAndTime(LocalDateTime.now());
			initialPayment.setPaymentStatus("success");
			initialPayment.setPaymentType("card");
			initialPayment.setRazor_payment_id(razorPayReqDTO.getRazorpay_payment_id());
			initialPayment.setRazor_signature(razorPayReqDTO.getRazorpay_signature());
			Payment2 successfulPay=pay2Dao.save(initialPayment);
			return "Payment successfully done and save";
		}
		return null;
	}
}
