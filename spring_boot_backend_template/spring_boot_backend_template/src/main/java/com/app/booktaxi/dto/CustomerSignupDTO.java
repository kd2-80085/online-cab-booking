package com.app.booktaxi.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * customer
id       name     email     password     mob.    booking_id
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomerSignupDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotEmpty(message = "First Name Required")
	private String firstName;
	
	@NotEmpty(message = "Last name Required")
	private String lastName;
	
	@Email(message = "Invalid Email!!!")
	@NotEmpty(message = "Email is Required")
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotEmpty(message = "Password id Required")
	@Size(min=8,max = 20,message = "Password Length Must be Between 8 to 20")
	private String password;
	
	@NotEmpty(message = "Mobile no. is Required")
	@Size  (min = 10, message = "Mobile no. should be of 10 digits")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile no. should be digits only")
	private String mobile;

}
