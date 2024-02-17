package com.app.booktaxi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthSignInDTO {
	
	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid Email Format")
	private String email;
	
	@NotBlank(message = "Password is Required")
	@Size(min=8,max = 20,message = "Password Length Must be Between 8 to 20")
	private String password;
	
	@NotBlank(message = "Role can not be empty")
	private String role;
}
