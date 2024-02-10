package com.app.booktaxi.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	private String status;
}
