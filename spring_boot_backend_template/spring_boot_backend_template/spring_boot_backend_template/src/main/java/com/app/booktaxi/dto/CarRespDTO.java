package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarRespDTO {
	
	private Long id;

	private String model;
	
	private String company;
	
	//private byte[] image;
	
	private Long ownerId;
	
	private int seatingCapacity;
	
	private String status;
	
	private Long driverId;
	
	private String registrationNo;
	
	private String taxiType;
	
	private String location;
	
}
