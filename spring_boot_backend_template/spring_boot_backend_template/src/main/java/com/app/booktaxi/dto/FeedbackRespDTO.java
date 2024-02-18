package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedbackRespDTO {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Long driverId;
		
	private String feedback;
	
	private int rating;

	private Long bookingId;	

}
