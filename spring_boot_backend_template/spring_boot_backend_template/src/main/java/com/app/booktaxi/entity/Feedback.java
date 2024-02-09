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
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
@ToString(exclude = {"booking","driver"},callSuper = true)
>>>>>>> Stashed changes
=======
@ToString(exclude = {"booking","driver"})
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
public class Feedback extends BaseEntity {
	
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
	@ManyToOne
	@JoinColumn(name = "driver_id",nullable = false)
	private Driver driver;
	
	@OneToOne
	@JoinColumn(name = "booking_id",nullable = false)
	private Booking booking;
	
>>>>>>> Stashed changes
=======
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
>>>>>>> 72f23b89119c9dbc68dc7d4b59f469a1843b3a50
	@Column(length = 50)
	private String feedback;
	
	@Column(name = "rating")
	private int rating;
	
	
}
