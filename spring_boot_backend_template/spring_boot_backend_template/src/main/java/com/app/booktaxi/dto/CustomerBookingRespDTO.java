package com.app.booktaxi.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerBookingRespDTO {

	
	private Long id;
	
	private LocalDateTime bookingDateTime;
	
	private String bookingStatus;
	
	private Long tripId;
	
	private String bookingType;
	
	private Long paymentId;
	
	private String taxiType;
	
	private float distance;
	
	private LocalDateTime pickupTime;

	private String pickUpLocation;
	
	private String dropLocation;
	
}
