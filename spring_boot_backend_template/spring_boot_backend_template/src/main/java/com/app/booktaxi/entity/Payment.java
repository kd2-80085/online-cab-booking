package com.app.booktaxi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Payment
Id      amount    booking_id     payment_status
 */

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "booking",callSuper = true)
public class Payment extends BaseEntity {


	@Column(name = "amount")
	private double amount;
	
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@Column(length = 25,name ="payment_status")
	private String paymentStatus;

	
	
	
}
