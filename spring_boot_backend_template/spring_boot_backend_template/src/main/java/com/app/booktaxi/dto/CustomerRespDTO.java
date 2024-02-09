package com.app.booktaxi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * customer
id       name     email     password     mob.    booking_id
 */

@Getter
@Setter
@ToString
public class CustomerRespDTO {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String mobile;

	
	
}
