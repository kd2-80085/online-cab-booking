package com.app.booktaxi.dto;


import lombok.Getter;
import lombok.Setter;

/*
 * customer
id       name     email     password     mob.    booking_id
 */

@Getter
@Setter
public class CustomerDTO {

	//private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String mobile;

	@Override
	public String toString() {
		return "CustomerDTO [ name=" + name + ", email=" + email + ", password=" + password + ", mobile="
				+ mobile + "]";
	}

	

	
}
