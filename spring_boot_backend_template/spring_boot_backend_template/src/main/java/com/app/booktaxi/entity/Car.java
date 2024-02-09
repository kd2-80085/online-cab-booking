package com.app.booktaxi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * car  
 id     model    company      owner_id     seating-capacity    status   driver_id   
registration_no          taxi_type (sedan)           location
 */

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString(exclude = {"driver","owner","trips","bookings"},callSuper = true)
public class Car extends BaseEntity {

	@Column(length = 25)
	private String model;
	
	@Column(length = 25)
	private String company;
	
	@Lob
	private byte[] image;
	
	@OneToMany(mappedBy = "car",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "owner_id",nullable = false)
	private Owner owner;
	
	@Column(name = "seating_capacity")
	private int seatingCapacity;
	
	@Column(length = 25)
	private String status;
	
	@OneToMany(mappedBy = "car",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Trip> trips = new ArrayList<Trip>();
	
	@OneToOne
	@JoinColumn(name="driver_id",nullable = false)
	private Driver driver;
	
	@Column(length = 10,name = "registration_no")
	private String registrationNo;
	
	@Column(length = 25, name = "taxi_type")
	private String taxiType;
	
	@Column(length = 100)
	private String location;
	
	public void addBooking(Booking b) {
		this.bookings.add(b);
		b.setCar(this);
	}
	
	public void removeBooking(Booking b) {
		this.bookings.remove(b);
		b.setCar(null);
	}
	
	public void addTrip(Trip t) {
		this.trips.add(t);
		t.setCar(this);
	}
	
public void removeTrio(Trip t) {
		this.trips.remove(t);
		t.setCar(null);
	}
	
}
