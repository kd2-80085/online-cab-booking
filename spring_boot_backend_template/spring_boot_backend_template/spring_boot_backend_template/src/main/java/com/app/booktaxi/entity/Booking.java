package com.app.booktaxi.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@ToString(exclude = {"customer","car","trip","driver","payments","feedbacks"},callSuper = true)
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
	@JoinColumn(name = "trip_id",nullable = true)
	private Trip trip;
	
	@ManyToOne
	@JoinColumn(name = "driver_id",nullable = true)
	private Driver driver;
	
	@Column(length = 25, name = "booking_type")
	private String bookingType;
	
	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Payment> payments;
	
	@Column(length = 25, name = "taxi_type")
	private String taxiType;
	
	@Column(name = "pickup_time")
	private LocalDateTime pickupTime;

	@Column(length = 100,name = "pick_up_location")
	private String pickupLocation;
	
	@Column(length = 100,name = "drop_location")
	private String dropLocation;
	
	@OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
	private Feedback feedbacks;
	
	public void addPayments(Payment p)	
	{
		this.payments.add(p);
		p.setBooking(this);
	}
	
	public void removePayments(Payment p) {
		this.payments.remove(p);
		p.setBooking(null);
	}

}