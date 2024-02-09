package com.app.booktaxi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Trip
id     cust_id    booking_id     trip_status   
 */

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"customers","bookings","car"},callSuper = true)
public class Trip extends BaseEntity {

<<<<<<< HEAD
<<<<<<< Updated upstream
	@Column(name = "customer_id")
	private int customerId;
=======
	@ManyToMany(mappedBy = "trips")
	private Set<Customer> customers = new HashSet<>();
>>>>>>> Stashed changes
=======
	@ManyToMany(mappedBy = "trips")
	private Set<Customer> customer=new HashSet<>();
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
	
	@OneToMany(mappedBy = "trip",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
	
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
	@ManyToOne
	@JoinColumn(name = "car_id",nullable = false)
=======
	@ManyToOne
	@JoinColumn(name = "car_id")
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
	private Car car;
	
	@Column(name = "start_time")
	private LocalDateTime startTime;
	
	@Column(name = "end_time")
	private LocalDateTime endTime;
	
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
	@Column(length = 25,name = "tripStatus")
	private String tripStatus;
	
	
	public void addBooking(Booking b) {
		this.bookings.add(b);
		b.setTrip(this);
	}
	
	public void removeBooking(Booking b) {
		this.bookings.remove(b);
		b.setTrip(null);
	}
}
