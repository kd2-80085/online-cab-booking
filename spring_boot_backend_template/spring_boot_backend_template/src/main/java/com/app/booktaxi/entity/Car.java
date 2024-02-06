package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@ToString(exclude = {"driver","owner"})
public class Car extends BaseEntity {

	@Column(length = 25)
	private String model;
	
	@Column(length = 25)
	private String company;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	@Column(name = "seating_capacity")
	private int seatingCapacity;
	
	@Column(length = 25)
	private String status;
	
	@OneToOne
	@JoinColumn(name="driver_id",nullable = false)
	private Driver driver;
	
	@Column(length = 10,name = "registration_no")
	private String registrationNo;
	
	@Column(length = 25, name = "taxi_type")
	private String taxiType;
	
	@Column(length = 100)
	private String location;
	
	
}
