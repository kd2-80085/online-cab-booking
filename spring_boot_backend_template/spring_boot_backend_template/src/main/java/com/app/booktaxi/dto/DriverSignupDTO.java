package com.app.booktaxi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DriverSignupDTO {
	
	@NotBlank(message = "First Name is required")
	private String firstName;
	
	@NotBlank(message = "Last Name is Required")
	private String lastName;	
	
	@NotBlank(message = "email required....")
	@Email(message = "Invalid email format!!")
	private String email;
	
	@NotBlank(message = "Password is Required")
	@Size(min=8,max = 20,message = "Password Length Must be Between 8 to 20")
	private String password;
	
	@NotBlank(message = "Mobile no. is Required")
	@Size  (min = 10, message = "Mobile no. should be of 10 digits")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile no. should be digits only")
	private String mobile;
	
	@NotBlank(message = "Licence No. required")
	private String licenceNo;
}
