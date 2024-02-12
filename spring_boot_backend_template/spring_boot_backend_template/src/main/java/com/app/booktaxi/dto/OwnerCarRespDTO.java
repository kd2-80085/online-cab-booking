package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OwnerCarRespDTO {

	private String model;
	
	private String company;
	
	private byte[] image;
	
	private String registrationNo;
	
	private String driverName;
	
	private String location;
	
	private String status;
	
}
