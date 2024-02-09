package com.app.booktaxi.dto;

import java.time.LocalDateTime;

import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Trip;

import lombok.*;

@Getter
@Setter
public class BookingRespDTO {
	
		private Long id;		
		
		private LocalDateTime bookingDateTime;
		
		
		private Customer customer;
		
		
		private int carId;
		
		
		private String bookingStatus;
		
		
		private Trip trip;
		
		
		private Driver driver;
		
		
		private String bookingType;
		
		
		private int paymentId;
		
		
		private String taxiType;
		
		
		private String distance;
		
		
		private LocalDateTime pickupTime;

		
		private String pickUpLocation;
		
		private String dropLocation;

		@Override
		public String toString() {
			return "BookingRespDTO [id=" + id + ", bookingDateTime=" + bookingDateTime + ", customer=" + customer
					+ ", carId=" + carId + ", bookingStatus=" + bookingStatus + ", trip=" + trip + ", driver=" + driver
					+ ", bookingType=" + bookingType + ", paymentId=" + paymentId + ", taxiType=" + taxiType
					+ ", distance=" + distance + ", pickupTime=" + pickupTime + ", pickUpLocation=" + pickUpLocation
					+ ", dropLocation=" + dropLocation + "]";
		}
		
		

}
