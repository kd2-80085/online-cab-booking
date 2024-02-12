package com.app.booktaxi.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRespDTO {
	
	private Long id;
	
	private double amount;
	
	private String paymentStatus;
	
	private LocalDateTime paymentDateAndTime;
	
	private String paymentType;
	
}
