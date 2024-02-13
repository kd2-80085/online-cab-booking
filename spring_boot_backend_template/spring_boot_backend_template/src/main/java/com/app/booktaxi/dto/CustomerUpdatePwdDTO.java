package com.app.booktaxi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerUpdatePwdDTO {

	@NotBlank(message = "Password is Required")
	@Size(min=8,max = 20,message = "Password Length Must be Between 8 to 20")
	private String oldPassword;
	
	@NotBlank(message = "Password is Required")
	@Size(min=8,max = 20,message = "Password Length Must be Between 8 to 20")
	private String newPassword;
}
