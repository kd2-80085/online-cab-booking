package com.app.booktaxi.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.service.AdminService;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.service.CarService;
import com.app.booktaxi.service.DriverService;
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

	@Autowired
	private DriverService driverService;

     //get all cars
	  //URL : http://localhost:8080/admin/cars
		  	//Method  : GET
		  	//req params  : pageNumber : def val 0, optional
		  	//			  pageSize : def val 3,, optional
		  	//resp : datached cars or exc
	  @GetMapping("/cars")
	  public ResponseEntity<?> getCars(@RequestParam(defaultValue = "0",required = false) int pageNumber,
			  	@RequestParam(defaultValue = "3", required = false) int pageSize) {
		  System.out.println("in get all car details "+ pageNumber + " " + pageSize);
		  
		  List<CarRespDTO> allCars = adminService.getAllCarsDetails(pageNumber, pageSize);
		  System.out.println("Admin Controller all cars\n");
		  if(allCars.isEmpty())
			  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		  return new ResponseEntity<>(allCars,HttpStatus.OK);
	  }
	  
	//get all drivers
	//URL : http://localhost:8080/admin/drivers
	  	//Method  : GET
	  	//req params  : pageNumber : def val 0, optional
	  	//			  	pageSize : def val 3,, optional
	  	//resp : datached drivers or exc
	  @GetMapping("/drivers")
	  public ResponseEntity<?> getDrivers(@RequestParam(defaultValue = "0",required = false) int pageNumber,
			  	@RequestParam(defaultValue = "3",required = false) int pageSize) {
		  System.out.println("in get all driver details "+ pageNumber+ " "+pageSize);
		  
		  List<DriverRespDTO> allDrivers = adminService.getAllDriversDetails(pageNumber,pageSize);
		  System.out.println("Admin Controller all drivers \n");
		  
		  if(allDrivers.isEmpty())
			  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		  return new ResponseEntity<>(allDrivers, HttpStatus.OK);
	  }
	  
	  
	//get payments on bookings
	//URL : http://localhost:8080//bookings/payments/{bookingId}/{paymentId}
	  	//Method  : GET
	  	//req params  : pageNumber : def val 0, optional
	  	//			 	pageSize : def val 3,, optional
	  	//resp : datached payments per trip or exc
	  @GetMapping("/bookings/payments/{bookingId}/{paymentId}")
	  public ResponseEntity<?> getPaymentByBooking(@PathVariable @NotNull Long bookingId, 
			  @PathVariable @NotNull Long paymentId) {
		  PaymentRespDTO bookingPayment = adminService.getPaymentByParticularBooking(bookingId, paymentId);
		  
		  System.out.println("Admin Controller payments trip wise \n");
		  if(bookingPayment==null)
			  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		  return new ResponseEntity<>(bookingPayment, HttpStatus.OK);
	  }
  
	  //get drivers feedbacks
	//URL : http://localhost:8080//drivers/feedbacks/{driverId}
	  	//Method  : GET
	  	//req params  : pageNumber : def val 0, optional
	  	//			 	pageSize : def val 3,, optional
	  	//resp : datached feedbacks or exc
	  @GetMapping("drivers/feedbacks/{driverId}")
	  public ResponseEntity<?> getDriversAllFeedbacks(@RequestParam(defaultValue = "0", required = false) int pageNumber,
				@RequestParam(defaultValue = "3", required = false) int pageSize, @PathVariable @NotNull Long driverId) {
		  System.out.println("in get all feedbacks of driver "+ pageNumber+ " "+pageSize);
		  List<FeedbackRespDTO> feedbacks = adminService.getDriverFeedback(pageNumber, pageSize, driverId);
		  
		  System.out.println("Admin Controller feedbacks of driver\n");
		  if(feedbacks.isEmpty())
			  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		  return new ResponseEntity<>(feedbacks, HttpStatus.OK);
	  }

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

	// approve Driver
	// URL : http://localhost:8080/admin/drivers/driverId
	// Method : put
	// resp : successful msg or exc
	@PutMapping("/drivers/{driverId}")
	public ResponseEntity<?> updateDriverStatus(@PathVariable @NotNull Long driverId) {
		System.out.println("in updateDriverIdStatus  ");

		String message = driverService.updateDriverStatus(driverId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	// delete car
	// URL : http://localhost:8080/admin/cars/carId
	// Method : delete
	// resp : successful msg
	@DeleteMapping("/cars/{carId}")
	public ResponseEntity<?> deleteCar(@PathVariable @NotNull Long carId) {
		System.out.println("in Admin Ctrller delete car  ");

		String message = carService.deleteCar(carId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	// delete Driver
	// URL : http://localhost:8080/admin/drivers/driverId
	// Method : delete
	// resp : successful msg
	@DeleteMapping("/drivers/{driverId}")
	public ResponseEntity<?> deleteDriver(@PathVariable @NotNull Long driverId) {
		System.out.println("in Admin Ctrller delete Driver ");

		String message = driverService.deleteDriver(driverId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	// delete Owner
	// URL : http://localhost:8080/admin/owners/ownerId
	// Method : delete
	// resp : successful msg
	@DeleteMapping("/owners/{ownerId}")
	public ResponseEntity<?> deleteOwner(@PathVariable @NotNull Long ownerId) {
		System.out.println("in Admin Ctrller delete Owner ");

		String message = ownerService.deleteOwner(ownerId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

}
