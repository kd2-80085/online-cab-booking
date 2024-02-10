package com.app.booktaxi.dto;

import java.util.Arrays;

import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRespDTO {
	
	private Long id;

	private String model;
	
	private String company;
	
	private byte[] image;
	
	private int seatingCapacity;
	
	private String status;
	
	private String registrationNo;
	
	private String taxiType;
	
	private String location;
	
	private Long ownerId;
	
	private Long driverId;

	@Override
	public String toString() {
		return "CarRespDTO [id=" + id + ", model=" + model + ", company=" + company + ", image="
				+ Arrays.toString(image) + ", seatingCapacity=" + seatingCapacity + ", status=" + status
				+ ", registrationNo=" + registrationNo + ", taxiType=" + taxiType + ", location=" + location + "]";
	}
	
	
	
}
