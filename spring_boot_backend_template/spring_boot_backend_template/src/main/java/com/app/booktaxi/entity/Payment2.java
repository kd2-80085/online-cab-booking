package com.app.booktaxi.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "payments2")

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "booking",callSuper = true)
public class Payment2 extends BaseEntity {
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name="razor_order_id")
	private String razor_order_id;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRazor_order_id() {
		return razor_order_id;
	}

	public void setRazor_order_id(String razor_order_id) {
		this.razor_order_id = razor_order_id;
	}

	public String getRazor_payment_id() {
		return razor_payment_id;
	}

	public void setRazor_payment_id(String razor_payment_id) {
		this.razor_payment_id = razor_payment_id;
	}

	public String getRazor_signature() {
		return razor_signature;
	}

	public void setRazor_signature(String razor_signature) {
		this.razor_signature = razor_signature;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPaymentDateAndTime() {
		return paymentDateAndTime;
	}

	public void setPaymentDateAndTime(LocalDateTime paymentDateAndTime) {
		this.paymentDateAndTime = paymentDateAndTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column
	private String razor_payment_id;
	
	@Column
	private String razor_signature;

	
	@ManyToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;
	
	@Column(length = 25,name ="payment_status")
	private String paymentStatus;
	
	@Column(name = "payment_date_time")
	private LocalDateTime paymentDateAndTime;
	
	@Column(name = "payment_type",length = 40)
	private String paymentType;
	
}
