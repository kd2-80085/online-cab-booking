package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
<<<<<<< Updated upstream
=======
@ToString(exclude = {"booking","driver"},callSuper = true)
>>>>>>> Stashed changes
public class Feedback extends BaseEntity {
	
<<<<<<< Updated upstream
=======
	@ManyToOne
	@JoinColumn(name = "driver_id",nullable = false)
	private Driver driver;
	
	@OneToOne
	@JoinColumn(name = "booking_id",nullable = false)
	private Booking booking;
	
>>>>>>> Stashed changes
	@Column(length = 50)
	private String feedback;
	
	@Column(name = "rating")
	private int rating;

	@Override
	public String toString() {
		return "Feedback [id=" +getId()+ ", customerId=" + customerId + ", feedback=" + feedback + ", rating=" + rating + "]";
	}
	
	
}
