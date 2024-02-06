package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * car  
 id     model    company      owner_id     seating-capacity    status   driver_id   
registration_no          taxi_type (sedan)           location
 */

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity {

	@Column(length = 25)
	private String model;
	
	@Column(length = 25)
	private String company;
	
	@Column(name = "owner_id")
	private int ownerId;
	
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

	@Override
	public String toString() {
		return "Car [id"+getId()+" ,model=" + model + ", company=" + company + ", ownerId=" + ownerId + ", seatingCapacity="
				+ seatingCapacity + ", status=" + status + ", driver=" + driver + ", registrationNo=" + registrationNo
				+ ", taxiType=" + taxiType + ", location=" + location + "]";
	}
	
	
	
}
