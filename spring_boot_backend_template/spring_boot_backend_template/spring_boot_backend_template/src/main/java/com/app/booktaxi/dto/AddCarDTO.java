package com.app.booktaxi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddCarDTO {

	@NotBlank(message = "Model name Required")
	private String model;
	
	@NotBlank(message = "Company name required")
	private String company;
	
	//private byte[] image;

	@NotNull(message="Seating Capacity required")
	private int seatingCapacity;
	
	
	private String status;
	
	private Long driverId;
	
	@NotBlank(message = "RegistrationNo is required")
	private String registrationNo;
	
	@NotBlank
	private String taxiType;
	
	@NotBlank
	private String location;
}