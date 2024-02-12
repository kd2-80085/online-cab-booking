package com.app.booktaxi.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.BookingReqDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.PaymentReqDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
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
	   

}
