package com.app.booktaxi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * driver
id      name     email     password     mob.     liscence-no.    rating

 */
@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"car","feedbacks","bookings"})
public class Driver extends BaseEntity{

	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String email;
	
	@Column(length = 12)
	private String password;
	
	@Column(name = "mobile", length = 13)
	private String mobile;
	
	@Column(length = 25)
	private String status;
	
	@OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
	
	@Column(length = 16,name = "licence_no")
	private String licenceNo;
	
	@Column(name = "rating")
	private int rating;

	@OneToOne(mappedBy = "driver",cascade = CascadeType.ALL,orphanRemoval = true)
	private Car car;
	
	@OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	
	public void addBooking(Booking b) {
		this.bookings.add(b);
		b.setDriver(this);
	}
	
	public void removeBooking(Booking b) {
		this.bookings.remove(b);
		b.setDriver(null);
	}
	
	public void addFeedback(Feedback f) {
		this.feedbacks.add(f);
		f.setDriver(this);
	}
	
	public void removeFeedback(Feedback f) {
		this.feedbacks.remove(f);
		f.setDriver(null);
	}
	
	
}
