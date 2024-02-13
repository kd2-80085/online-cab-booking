package com.app.booktaxi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerCarDTO {

	private Long id;
	
	private String model;
	
	private String company;
	
	private byte[] image;
	
	private int seatingCapacity;
	
	private String driverName;
	
	private String driverMobile;
	
	private String registrationNo;
	
	private String taxiType;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
//	private String status;
	
}
