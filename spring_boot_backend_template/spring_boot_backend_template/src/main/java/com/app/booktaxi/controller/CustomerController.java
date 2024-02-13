package com.app.booktaxi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dto.CustomerBookingRespDTO;
import com.app.booktaxi.dto.CustomerCarDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.PaymentReqDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.dto.CustomerUpdateProfileDTO;
import com.app.booktaxi.dto.CustomerUpdatePwdDTO;
import com.app.booktaxi.dto.FeedbackDTO;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	public CustomerController() {
		System.out.println("in customer controller");
	}

	@Autowired
	private CustomerService custService;

	// URL : http://localhost:8080/customer/signup/
	// Method : POST
	// req params : in Body
	// (fname,lname,email,password,mobile)
	// resp : (id,fname,lname,email,mobile)
	@PostMapping("/signup")
	public ResponseEntity<?> addCustomer(@RequestBody @Valid CustomerSignupDTO c) {
		System.out.println(c);
		System.out.println("in add new customer");
//		return custService.addNewCustomer(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(custService.addNewCustomer(c));
	}
	
	// URL : http://localhost:8080/customer/bookings/{customerId}
	// Method : GET
	// req params : in Head  => customerId
    //                
	// resp :  List of Bookings
	
	@GetMapping("/bookings/{customerId}")
	public ResponseEntity<?> getBookings(@RequestParam(defaultValue = "0",required = false) int pageNumber,
			                             @RequestParam(defaultValue = "3", required = false) int pageSize,  
			                             @PathVariable @NotNull Long customerId)
	{
		System.out.println("In showBookings = "+customerId+" ,"+pageNumber+" ,"+pageSize);
		List<CustomerBookingRespDTO> customerBookingsList = custService.getBookingByCustomer(pageNumber, pageSize, customerId);
		
		if(customerBookingsList.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		else	
		return new ResponseEntity<>(customerBookingsList, HttpStatus.OK);
		
	}
	
	// URL : http://localhost:8080/customer/feedbacks/
		// Method : POST
		// req params :  =>In Body 
	                            //(bookingId, feedback, rating)      
		// resp : (id ,bookingId,feedback ,rating)
	@PostMapping ("/feedbacks")
	public ResponseEntity<?> addFeedback(@RequestBody @Valid FeedbackDTO fdto){
		System.out.println("FeedbackDTO Values = "+fdto);
		return ResponseEntity.status(HttpStatus.CREATED).body(custService.addNewFeedback(fdto));
	}
	
	// URL : http://localhost:8080/customer/bookings/{location}
			// Method : GET
			// req params : in Head - (location)      
			// resp : (id, model, company, image, seatingCapacity, driverName,
	        //          driverMobile, registrationNumber, taxiType )
	@GetMapping("/bookings/cars/{location}")
	public ResponseEntity<?> getCars(@RequestParam (defaultValue = "0",required = false) int pageNumber,
			                         @RequestParam (defaultValue = "5",required = false) int pageSize,
			                         @PathVariable String location)
	{
		System.out.println("In getCars = "+location+" ,"+pageNumber+" ,"+pageSize);
		List<CustomerCarDTO> carList = custService.getCarsByLocation(pageNumber,pageSize,location);
		if(carList.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		else
		return new ResponseEntity<>(carList, HttpStatus.OK);
	}

	// URL : http://localhost:8080/customer/cars/bookcar
	// Method : POST
	// req params : in Body
	// bookingDateTime,customerId, carId, driverId, bookingType,taxiType, distance,
	// pickupTime, pickUpLocation,dropLocation
	// resp : (booking id with other details)
	@PostMapping("/cars/payment/bookcar")
	public ResponseEntity<?> bookCab(@RequestBody @Valid BookingReqDTO bookingReqDto) {
		System.out.println(bookingReqDto);
		System.out.println("in book cab");
		return ResponseEntity.status(HttpStatus.CREATED).body(custService.bookCab(bookingReqDto));
	}

	// URL : http://localhost:8080/customer/cars/bookcar/payment
	// Method : POST
	// req params : in Body
	// paymentId,amount, bookingId, transaction_id,paymentStatus,paymentDateAndTime,
	// paymentType;
	// resp : (booking id with other details)
	@PostMapping("/cars/payment/bookcar/payment")
	public ResponseEntity<?> saveNewPayment(@RequestBody @Valid PaymentReqDTO paymentReqDTO) {
		System.out.println(paymentReqDTO);
		System.out.println("in saveNewPayment");
		PaymentRespDTO paymentRespDTO = custService.saveNewPayment(paymentReqDTO);
		if (paymentRespDTO != null)
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Payment saved successfully: " + paymentReqDTO);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save payment");
	}
	
	// URL : http://localhost:8080/customer/bookings/{bookingid}
	// Method : put
	@PutMapping("/bookings/{bookingid}")
	public ResponseEntity<?> cancelBooking(@PathVariable @NotNull Long bookingid) 
	{
		System.out.println("in updateCarStatus  ");

		String message = custService.cancelBooking(bookingid);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}
	   
	// URL : http://localhost:8080/customer/bookings/payments/{bookingId}
				// Method : GET
				// req params : in Head - (customerId)      
				// resp : (id, model, company, image, seatingCapacity, driverName,
		        //          driverMobile, registrationNumber, taxiType )
	@GetMapping("/bookings/payments/{bookingId}")
	public ResponseEntity<?> getPayment(@PathVariable Long bookingId){
		System.out.println("int getPayment "+bookingId);
		return ResponseEntity.status(HttpStatus.OK).body(custService.getPaymentDetails(bookingId));
	}
	
	// URL : http://localhost:8080/customer/profile/{customerId}
	// Method : GET
	// req params : in Head - (customerId)      
	// resp : (id,fname,lname,email,mobile)
	@GetMapping("/profile/{customerId}")
	public ResponseEntity<?> getProfile(@PathVariable Long customerId){
		return ResponseEntity.status(HttpStatus.OK).body(custService.getProfileDetails(customerId));	
		
	}
	
	// URL : http://localhost:8080/customer/profile/{customerId}
		// Method : PUT
		// req params : in Head - (customerId)   
	    //              in Body - (ffname,lname,email,mobile)
		// resp : (id,fname,lname,email,mobile)
	@PutMapping("/profile/{customerId}")
	public ResponseEntity<?> updateProfile(@PathVariable Long customerId, @RequestBody CustomerUpdateProfileDTO custDTO){
		System.out.println("In updateProfile : "+customerId+" "+custDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(custService.updateProfileDetails(customerId,custDTO));
	}
	
	// URL : http://localhost:8080/customer/profile/{customerId}
			// Method : PUT
			// req params : in Head - (customerId)   
		    //              in Body - (oldPassword, newPassword)
			// resp : (id,fname,lname,email,mobile)
	@PutMapping("/password/{customerId}")
	public ResponseEntity<?> updatePassword(@PathVariable Long customerId,@RequestBody CustomerUpdatePwdDTO passDTO){
		System.out.println("In updatePassword : "+customerId+" "+passDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(custService.updatePassword(customerId,passDTO));
	}

}
