package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
