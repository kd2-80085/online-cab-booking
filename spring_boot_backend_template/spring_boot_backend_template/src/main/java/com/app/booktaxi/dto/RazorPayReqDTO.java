package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class RazorPayReqDTO {

	private String razorpay_payment_id;
	private String razorpay_order_id;
	private String razorpay_signature;
	
}
