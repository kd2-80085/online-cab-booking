package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRespDTO {
	
	private Long id;
	
	private double amount;
	
	private Long bookingId;
	
	private String paymentStatus;
	
}
