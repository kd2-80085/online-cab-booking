package com.app.booktaxi.dto;

import java.time.LocalDateTime;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Payment;
import com.app.booktaxi.entity.Trip;

import lombok.*;

@Getter
@Setter

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

	@Override
	public String toString() {
		return "BookingRespDTO [id=" + id + ", bookingDateTime=" + bookingDateTime + ", bookingStatus=" + bookingStatus
				+ ", customerId=" + customerId + ", carId=" + carId + ", tripId=" + tripId + ", driverId=" + driverId
				+ ", bookingType=" + bookingType + ", taxiType=" + taxiType + ", distance="
				+ distance + ", pickupTime=" + pickupTime + ", pickUpLocation=" + pickUpLocation + ", dropLocation="
				+ dropLocation + "]";
	}

}
