package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DistanceRespDTO {
	
	private String pickupLocation;
	
	private String dropLocation;
	
	private float distance;
}
