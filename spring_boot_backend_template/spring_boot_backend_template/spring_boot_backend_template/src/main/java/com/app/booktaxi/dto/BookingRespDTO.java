package com.app.booktaxi.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@ToString
public class BookingRespDTO {
	private Long id;

	private LocalDateTime bookingDateTime;

	private String bookingStatus;

	private Long customerId;
	private Long carId;
	private Long tripId;
	private Long driverId;
	//private Long paymentId;

	private String bookingType;

	private String taxiType;

	private String distance;

	private LocalDateTime pickupTime;

	private String pickUpLocation;

	private String dropLocation;

}
