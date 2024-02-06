package com.app.booktaxi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Payment
Id      amount    booking_id     payment_status
 */

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {


	@Column(name = "amount")
	private double amount;
	
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(length = 25,name ="payment_status")
	private String paymentStatus;

	@Override
	public String toString() {
		return "Payment [id=" +getId()+ ", amount=" + amount + ", bookingId=" + bookingId + ", paymentStatus=" + paymentStatus + "]";
	}
	
	
}
