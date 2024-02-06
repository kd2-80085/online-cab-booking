package com.app.booktaxi.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
 * booking
 * id     booking_date_time      customer_id       car_id      booking_status                                                 
                       driver_id       booking_type(share,solo)       payment_id                  		
distance   pickup_time       pick_up_location      drop_location
 */
@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking extends BaseEntity {
	
	@Column(name = "booking_date_time")
	private LocalDateTime bookingDateTime;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@Column(name = "car_id")
	private int carId;
	
	@Column(length = 25,name ="booking_status")
	private String bookingStatus;
	
	@Column(name = "driver_id")
	private int driverId;
	
	@Column(length = 25, name = "booking_type")
	private String bookingType;
	
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(length = 25, name = "taxi_type")
	private String taxiType;
	
	@Column(length = 100)
	private String distance;
	
	@Column(name = "pickup_time")
	private LocalDateTime pickupTime;

	@Column(length = 100,name = "pick_up_location")
	private String pickUpLocation;
	
	@Column(length = 100,name = "drop_location")
	private String dropLocation;
	
}
