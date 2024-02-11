package com.app.booktaxi.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class BookingReqDTO 
{

	private LocalDateTime bookingDateTime;

	private Long customerId;
	
	private Long carId;
	
	private Long driverId;
	
	private String bookingStatus;
	
	private String bookingType;

	private String taxiType;

	private String distance;

	private LocalDateTime pickupTime;

	private String pickUpLocation;

	private String dropLocation;
	

}
