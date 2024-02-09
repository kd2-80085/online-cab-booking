package com.app.booktaxi.controller;

import java.util.List;

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

import com.app.booktaxi.dto.AuthDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.service.AdminService;
import com.app.booktaxi.service.CarService;
import com.app.booktaxi.service.OwnerService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private CarService carService;
//	@PostMapping
//	public ResponseEntity<?>  customerLogin(@RequestBody AuthDTO auth) {
//		return ResponseEntity.ok(adminService.doLogin(auth));
//	}

	// public ResponseEntity<List<TripBooking>> getTripsByCustomer( @RequestParam
	// String key, @RequestParam Integer customerId) throws CustomerException,
	// TripBookingException, LogInException {

	// get individual customer bookings
	// URL : http://localhost:8080/admin/trips/bookings/customerId
	// Method : GET
	// req params : pageNumber , def val 0 , optional
	// pageSize : def val 3 , optional
	// resp : detached bookings or exc
	@GetMapping("/trips/bookings/{customerId}")
	public ResponseEntity<?> getTripsByCustomer(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize, @PathVariable @NotNull Long customerId) {
		System.out.println("in get booking for cust " + pageNumber + " " + pageSize);

		List<BookingRespDTO> tripBookings = adminService.getTripsByCustomer(pageNumber, pageSize, customerId);
		System.out.println("Admin Controller tripbookings \n");

		if (tripBookings.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		// bookings found
		return new ResponseEntity<>(tripBookings, HttpStatus.OK);

	}

	// approve car
	// URL : http://localhost:8080/admin/cars/carId
	// Method : put
	// resp : successful msg or exc
	@PutMapping("/cars/{carId}")
	public ResponseEntity<?> updateCarStatus(@PathVariable @NotNull Long carId) {
		System.out.println("in updateCarStatus  ");

		String message = carService.updateCarStatus(carId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	// approve owner
	// URL : http://localhost:8080/admin/owners/ownerId
	// Method : put
	// resp : successful msg or exc
	@PutMapping("/owners/{ownerId}")
	public ResponseEntity<?> updateOwnerStatus(@PathVariable @NotNull Long ownerId) {
		System.out.println("in updateOwnerStatus  ");

		String message = ownerService.updateOwnerStatus(ownerId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

}
