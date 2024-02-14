package com.app.booktaxi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
public class CustomerUpdateProfileDTO {
	
	@NotBlank(message = "First name required")
	private String firstName;
	
	@NotBlank(message = "Last name required")
	private String lastName;
	
	@NotBlank(message = "Email required")
	@Email(message = "Invalid Email!!!")
	private String email;
	
	@NotBlank(message = "Mobile no. is Required")
	@Size  (min = 10, message = "Mobile no. should be of 10 digits")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile no. should be digits only")
	private String mobile;

	
}
