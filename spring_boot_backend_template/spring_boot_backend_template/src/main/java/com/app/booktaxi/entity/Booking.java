package com.app.booktaxi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * booking
 * id     booking_date_time      customer_id       car_id      booking_status                                                 
                       driver_id       booking_type(share,solo)       payment_id                  		
distance   pickup_time       pick_up_location      drop_location
 */
@Entity
@Table(name = "bookings")
@Getter
@Setter
@ToString(exclude = {"customer","car","trip","driver","payment","feedbacks"},callSuper = true)
public class Booking extends BaseEntity{
	
	@Column(name = "booking_date_time")
	private LocalDateTime bookingDateTime;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "car_id",nullable = false)
	private Car car;
	
	@Column(length = 25,name ="booking_status")
	private String bookingStatus;
	
	@ManyToOne
	@JoinColumn(name = "trip_id",nullable = false)
	private Trip trip;
	
	@ManyToOne
	@JoinColumn(name = "driver_id",nullable = false)
	private Driver driver;
	
	@Column(length = 25, name = "booking_type")
	private String bookingType;
	
	@OneToOne(mappedBy = "booking",cascade = CascadeType.ALL,orphanRemoval = true)
	private Payment payment;
	
	@Column(length = 25, name = "taxi_type")
	private String taxiType;
	
	private float distance;
	
	@Column(name = "pickup_time")
	private LocalDateTime pickupTime;

	@Column(length = 100,name = "pick_up_location")
	private String pickUpLocation;
	
	@Column(length = 100,name = "drop_location")
	private String dropLocation;
	
	@OneToOne(mappedBy = "booking",cascade = CascadeType.ALL,orphanRemoval = true)
	private Feedback feedbacks;

}
