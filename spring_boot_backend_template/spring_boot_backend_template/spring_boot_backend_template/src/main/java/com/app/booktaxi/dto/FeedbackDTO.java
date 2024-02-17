package com.app.booktaxi.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedbackDTO {

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long driverId;
	
	@NotNull
	private Long bookingId;
	
	@NotBlank(message = "Feedback can not be empty")
	private String feedback;
	
	@NotNull
	@Min(value = 1, message = "Lowest rating is 1 ")
	@Max(value = 5, message = "Highest rsting is 5 ")
	private int rating;
}
