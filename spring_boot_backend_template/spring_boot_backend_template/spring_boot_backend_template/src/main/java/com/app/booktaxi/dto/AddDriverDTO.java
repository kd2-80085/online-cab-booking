package com.app.booktaxi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddDriverDTO {
	
	@NotBlank(message = "first name must be supplied!!!")
	private String firstName;
	
	private String lastName;	
	
	@NotBlank(message = "email required....")
	@Email(message = "Invalid email format!!")
	private String email;
	
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid password")
	private String password;
	private String mobile;
	
	private String licenceNo;
}
