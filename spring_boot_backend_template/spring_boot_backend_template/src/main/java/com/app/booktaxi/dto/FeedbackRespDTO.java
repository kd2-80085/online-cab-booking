package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedbackRespDTO {
	
	private int id;
	
	private Long driverId;
		
	private String feedback;
	
	private int rating;
}
