package com.app.booktaxi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

<<<<<<< Updated upstream
	@Column(name = "customer_id")
	private int customerId;
=======
	@ManyToMany(mappedBy = "trips")
	private Set<Customer> customers = new HashSet<>();
>>>>>>> Stashed changes
	
	@OneToMany(mappedBy = "trip",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
	
<<<<<<< Updated upstream
=======
	@ManyToOne
	@JoinColumn(name = "car_id",nullable = false)
	private Car car;
	
	@Column(name = "start_time")
	private LocalDateTime startTime;
	
	@Column(name = "end_time")
	private LocalDateTime endTime;
	
>>>>>>> Stashed changes
	@Column(length = 25,name = "tripStatus")
	private String tripStatus;
}
