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
public class CustomerSigninDTO {

	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid Email Format")
	private String email;
	
	@NotBlank
	@Size(min=8,max = 20,message = "Inavlid Password")
	private String password;
}
