package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * owner
        id       name      email       password        mob.      is_driver
 */

@Entity
@Table(name = "owner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends BaseEntity {

	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String email;
	
	@Column(length = 12)
	private String password;
	
	@Column(length = 13)
	private String mobile;
	
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(name = "car_id")
	private int carId;
	
	@Column(length = 25,name = "is_driver")
	private String isDriver;

	@Override
	public String toString() {
		return "Owner [id=" +getId()+ ", name=" + name + ", email=" + email + ", password=" + password + ", mobile=" + mobile
				+ ", bookingId=" + bookingId + ", isDriver=" + isDriver + "]";
	}
	
	
}
