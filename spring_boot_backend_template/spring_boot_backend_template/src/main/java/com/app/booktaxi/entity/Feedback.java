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
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends BaseEntity {

	@Column(name = "customer_id")
	private int customerId;
	
	@Column(length = 50)
	private String feedback;
	
	@Column(name = "rating")
	private int rating;

	@Override
	public String toString() {
		return "Feedback [id=" +getId()+ ", customerId=" + customerId + ", feedback=" + feedback + ", rating=" + rating + "]";
	}
	
	
}
