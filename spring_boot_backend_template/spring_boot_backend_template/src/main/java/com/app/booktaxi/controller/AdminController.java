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

import com.app.booktaxi.dto.AuthDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.dto.PaymentRespDTO;
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
	  
	//URL : http://localhost:8080//drivers/feedbacks/{driverId}
	  	//Method  : GET
	  	//req params  : pageNumber : def val 0, optional
	  	//			 	pageSize : def val 3,, optional
	  	//resp : datached feedbacks or exc
	  @GetMapping("drivers/feedbacks/{driverId}")
	  public ResponseEntity<?> getFeedback(@PathVariable @NotNull Long driverId) {
		  FeedbackRespDTO feedback = adminService.getDriverFeedback(driverId);
		  
		  System.out.println("Admin Controller payments trip wise \n");
		  if(feedback==null)
			  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		  return new ResponseEntity<>(feedback, HttpStatus.OK);
	  }
	  
	  
	  
}
