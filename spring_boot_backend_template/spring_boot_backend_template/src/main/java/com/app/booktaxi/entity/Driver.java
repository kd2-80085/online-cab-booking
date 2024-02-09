package com.app.booktaxi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
<<<<<<< Updated upstream
@ToString(exclude = "car")
=======
@ToString(exclude = {"car","feedbacks","bookings"},callSuper = true)
>>>>>>> Stashed changes
public class Driver extends BaseEntity{

	@Column(length = 50)
	private String firstName;
	
	@Column(length = 50)
	private String lastName;
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 80)
	private String password;
	
	@Column(name = "mobile", length = 10)
	private String mobile;
	
	@Lob
	private byte[] image;
	
	@Column(length = 15)
	private String status;
	
	@OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
	
	@Column(length = 16,name = "licence_no")
	private String licenceNo;
	
	@Column(name = "rating")
	private int rating;

	@OneToOne(mappedBy = "driver")
	private Car car;
}
