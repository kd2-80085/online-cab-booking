package com.app.booktaxi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
