package com.app.booktaxi.entity;


import java.time.LocalDateTime;

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
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
@ToString(exclude = "booking",callSuper = true)
>>>>>>> Stashed changes
=======
@ToString(exclude = "booking")
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
public class Payment extends BaseEntity {
	
	@Column(name = "amount")
	private double amount;
	
<<<<<<< HEAD
<<<<<<< Updated upstream
	@Column(name = "booking_id")
	private int bookingId;
=======
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
	
	@Column(length = 25,name ="payment_status")
	private String paymentStatus;

<<<<<<< HEAD
	@Override
	public String toString() {
		return "Payment [id=" +getId()+ ", amount=" + amount + ", bookingId=" + bookingId + ", paymentStatus=" + paymentStatus + "]";
	}
=======
	@OneToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;
=======
	
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
	
	@Column(length = 25,name ="payment_status")
	private String paymentStatus;
	
	@Column(name = "payment_date_time")
	private LocalDateTime paymentDateAndTime;
>>>>>>> Stashed changes
	
	@Column(name = "payment_type",length = 40)
	private String paymentType;
	
}
