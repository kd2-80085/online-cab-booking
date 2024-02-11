package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DriverRespDTO {
	
	private Long Id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String mobile;
	
	private String licenceNo;
	
	private int rating;
	
	private String status;
	
	private Long carId;
}
