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
	
	//private byte[] image;
	
	private Long ownerId;
	
	private int seatingCapacity;
	
	private String status;
	
	private Long driverId;
	
	private String registrationNo;
	
	private String taxiType;
	
	private String location;

	private Long ownerId;
	
	private Long driverId;

	@Override
	public String toString() {
		return "CarRespDTO [id=" + id + ", model=" + model + ", company=" + company + ", ownerId=" + ownerId
				+ ", seatingCapacity=" + seatingCapacity + ", status=" + status + ", driverId=" + driverId
				+ ", registrationNo=" + registrationNo + ", taxiType=" + taxiType + ", location=" + location + "]";
	}
	
	
}
