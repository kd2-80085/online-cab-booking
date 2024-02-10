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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dto.CustomerBookingRespDTO;
import com.app.booktaxi.dto.CustomerCarDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
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
	            //                (fname,lname,email,password,mobile) 
				// resp :  (id,fname,lname,email,mobile)
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
	
	
	
	
	
	
}
