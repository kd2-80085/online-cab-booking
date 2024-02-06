package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * driver
id      name     email     password     mob.     liscence-no.    rating

 */
@Entity
@Table(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseEntity {

	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String email;
	
	@Column(length = 12)
	private String password;
	
	@Column(length = 13)
	private String mobile;
	
	@Column(length = 16,name = "licence_no")
	private String licenceNo;
	
	@Column(name = "rating")
	private int rating;

	@OneToOne(mappedBy = "driver")
	private Car car;
	
	@Override
	public String toString() {
		return "Driver [id=" +getId()+ ", name=" + name + ", email=" + email + ", password=" + password + ", mobile=" + mobile
				+ ", licenceNo=" + licenceNo + ", rating=" + rating + "]";
	}
	
	
	
	
}
