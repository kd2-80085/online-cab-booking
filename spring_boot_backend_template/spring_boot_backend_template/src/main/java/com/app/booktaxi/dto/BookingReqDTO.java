
package com.app.booktaxi.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingReqDTO {

//	@JsonProperty(access = Access.WRITE_ONLY)
//	private Long id;

	private Long customerId;

	private Long carId;

	private Long driverId;

	private String bookingType;

	private String taxiType;

	private LocalDateTime pickupTime;

	private String pickupLocation;

	private String dropLocation;

	@JsonProperty(access = Access.READ_ONLY)
	private double amount;

//	bookingType
//	: 
//	"Advance"
//	carId
//	: 
//	3
//	customerId
//	: 
//	4
//	driverId
//	: 
//	3
//	dropLocation
//	: 
//	"Pune"
//	pickupLocation
//	: 
//	"Nashik"
//	pickupTime
//	: 
//	"2024-02-18T12:28"
//	taxiType
//	: 
//	"Sedan"
}
