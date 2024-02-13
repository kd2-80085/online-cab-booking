package com.app.booktaxi.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.CustomerUpdateProfileDTO;
import com.app.booktaxi.dto.DriverUpdateProfileDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.service.CarService;
import com.app.booktaxi.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {

	@Autowired
	private DriverService driverService;

	// Get All Cars By Driver Id.
	// URL : http://localhost:8080/driver/cars/{driverId}
	// Method : GET
	// req params : pageNumber : def val 0, optional
	// pageSize : def val 3,, optional
	// resp : detached cars or exc
	@GetMapping("/cars/{driverId}")
	public ResponseEntity<?> getCars(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize, @PathVariable @NotNull Long driverId) {
		System.out.println("in get all car details " + pageNumber + " " + pageSize);

		List<CarRespDTO> allCars = driverService.getAllCarsDetailsByDriver(pageNumber, pageSize, driverId);
		System.out.println("Driver Controller all cars\n");
		if (allCars.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return new ResponseEntity<>(allCars, HttpStatus.OK);

	}

	// Get All Incoming Bookings By Driver Id.
	// URL : http://localhost:8080/driver/bookings/{driverId}
	// Method : GET
	// req params : pageNumber : def val 0, optional
	// pageSize : def val 3,, optional
	// resp : detached cars or exc
	@GetMapping("/bookings/{driverId}")
	public ResponseEntity<?> getBookings(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize, @PathVariable @NotNull Long driverId) {
		System.out.println("in get all booking details " + pageNumber + " " + pageSize);

		List<BookingRespDTO> allBookings = driverService.getIncomingBookingsForDriver(pageNumber, pageSize, driverId);
		System.out.println("Driver Controller all bookings\n");
		if (allBookings.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return new ResponseEntity<>(allBookings, HttpStatus.OK);

	}

	// Get All FeedBacks By Driver Id.
	// URL : http://localhost:8080/driver/feedbacks/{driverId}
	// Method : GET
	// req params : pageNumber : def val 0, optional
	// pageSize : def val 3,, optional
	// resp : detached cars or exc
	@GetMapping("/feedbacks/{driverId}")
	public ResponseEntity<?> getFeedBacks(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize, @PathVariable @NotNull Long driverId) {
		System.out.println("in get all FeedBack details " + pageNumber + " " + pageSize);

		List<FeedbackRespDTO> allFeedBacks = driverService.getFeedbacksForDriver(pageNumber, pageSize, driverId);
		System.out.println("Driver Controller all FeedBacks\n");
		if (allFeedBacks.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return new ResponseEntity<>(allFeedBacks, HttpStatus.OK);

	}
	
	// URL : http://localhost:8080/driver/profile/{driverId}
			// Method : PUT
			// req params : in Head - (driverId)   
		    //              in Body - (fname,lname,email,mobile,licenseNo)
			// resp : (id,fname,lname,email,mobile,licenseNo)
	@PutMapping("/profile/{driverId}")
	public ResponseEntity<?> updateProfile(@PathVariable Long driverId, 
			@RequestBody DriverUpdateProfileDTO driverDto){
		System.out.println("In update Profile : "+driverId+" "+driverDto);
		return ResponseEntity.status(HttpStatus.OK)
				.body(driverService.updateProfileDetails(driverId,driverDto));
	}

}
