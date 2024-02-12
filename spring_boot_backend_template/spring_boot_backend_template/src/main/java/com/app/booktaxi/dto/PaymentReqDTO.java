package com.app.booktaxi.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentReqDTO {
	
	private double amount;

	private Long bookingId;
	
	private String transaction_id;
	
	private String paymentStatus;
	
	private LocalDateTime paymentDateAndTime;
	
	private String paymentType;
	
	

}
