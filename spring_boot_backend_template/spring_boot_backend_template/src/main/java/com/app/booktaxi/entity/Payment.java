package com.app.booktaxi.entity;


import java.time.LocalDateTime;

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
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< Updated upstream
=======
@ToString(exclude = "booking",callSuper = true)
>>>>>>> Stashed changes
public class Payment extends BaseEntity {
	
	@Column(name = "amount")
	private double amount;
	
<<<<<<< Updated upstream
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(length = 25,name ="payment_status")
	private String paymentStatus;

	@Override
	public String toString() {
		return "Payment [id=" +getId()+ ", amount=" + amount + ", bookingId=" + bookingId + ", paymentStatus=" + paymentStatus + "]";
	}
=======
	@OneToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;
	
	@Column(length = 25,name ="payment_status")
	private String paymentStatus;
	
	@Column(name = "payment_date_time")
	private LocalDateTime paymentDateAndTime;
>>>>>>> Stashed changes
	
	@Column(name = "payment_type",length = 40)
	private String paymentType;
	
}
