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

@Getter
@Setter
@ToString
public class CarUpdateDTO {

	@NotBlank(message = "Model name required")
	private String model;
	
	@NotBlank(message = "Company name required")
	private String company;
	
	@NotNull(message = "Seating Capacity required.")
	private int seatingCapacity;
	
	@NotBlank(message = "TaxiType is required")
	private String taxiType;
	
	@NotBlank(message = "Location is required")
	private String location;


}