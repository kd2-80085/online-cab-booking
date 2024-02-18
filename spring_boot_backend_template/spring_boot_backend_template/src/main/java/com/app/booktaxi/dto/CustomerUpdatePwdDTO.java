package com.app.booktaxi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerUpdatePwdDTO {

	@NotBlank(message = "Password is Required")
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{8,20})",message = "Invalid password")
	private String oldPassword;
	
	@NotBlank(message = "Password is Required")
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{8,20})",message = "Invalid password")
	private String newPassword;
}
