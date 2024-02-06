package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Trip
Id     cust_id    booking_id     trip_status   
 */

@Entity
@Table(name = "trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends BaseEntity {

	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(length = 25,name = "tripStatus")
	private String tripStatus;

	@Override
	public String toString() {
		return "Trip [id=" +getId()+ ", customerId=" + customerId + ", bookingId=" + bookingId + ", tripStatus=" + tripStatus + "]";
	}

	
	
}
