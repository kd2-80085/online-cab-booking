package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Feedback
            id        cust_id      feedback    rating
 */

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString(exclude = {"booking","driver"},callSuper = true)
public class Feedback extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "driver_id",nullable = false)
	private Driver driver;
	
	@OneToOne
	@JoinColumn(name = "booking_id",nullable = false)
	private Booking booking;
	
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@Column(length = 50)
	private String feedback;
	
	@Column(name = "rating")
	private int rating;
	
	
}
