package com.app.booktaxi.controller;

import java.util.List;

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


import com.app.booktaxi.dto.CustomerSigninDTO;
 
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
//	@PostMapping
//	public ResponseEntity<?>  customerLogin(@RequestBody AuthDTO auth) {
//		return ResponseEntity.ok(adminService.doLogin(auth));
//	}

   // public ResponseEntity<List<TripBooking>> getTripsByCustomer( @RequestParam String key, @RequestParam Integer customerId) throws CustomerException, TripBookingException, LogInException {

	
	
	// URL : http://localhost:8080/admin/trips/bookings/customerId
			// Method : GET
			// req params : pageNumber , def val 0 , optional
			//			    pageSize : def val 3 , optional
			// resp : detached bookings or exc
	  @GetMapping("/trips/bookings/{customerId}")
		public ResponseEntity<?> getTripsByCustomer(@RequestParam(defaultValue = "0", required = false) int pageNumber,
				@RequestParam(defaultValue = "3", required = false) int pageSize,
				@PathVariable @NotNull Long customerId) {
			System.out.println("in get booking for cust " + pageNumber + " " + pageSize);
		//	List<EmpRespDTO> list = empService.getAllEmployees(pageNumber, pageSize);
			
			List<BookingRespDTO> tripBookings = adminService.getTripsByCustomer(pageNumber, pageSize,customerId); 
			System.out.println("Admin Controller tripbookings \n"+tripBookings);
			if (tripBookings.isEmpty())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			// bookings found
			return new ResponseEntity<>(tripBookings, HttpStatus.OK);
		}
	  
	  
}
