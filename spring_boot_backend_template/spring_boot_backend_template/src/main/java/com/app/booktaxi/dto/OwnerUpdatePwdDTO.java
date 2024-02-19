package com.app.booktaxi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OwnerUpdatePwdDTO {
	@NotEmpty(message = "Password id Required")
	@Pattern(regexp="((?=.\\d)(?=.[a-z])(?=.[#@$]).{8,20})",message = "Invalid password")	
	private String oldPassword;
	
	@NotEmpty(message = "Password id Required")
	@Pattern(regexp="((?=.\\d)(?=.[a-z])(?=.[#@$]).{8,20})",message = "Invalid password")	
	private String newPassword;

}
