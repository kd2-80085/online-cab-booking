package com.app.booktaxi.entity;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * customer
id       name     email     password     mob.    booking_id
 */

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"bookings","trips"},callSuper = true)
public class Customer extends BaseEntity {
	
	@Column(length = 25)
	private String name;
	
	@Column(length = 35)
	private String email;
	
	@Column(length = 12)
	private String password;
	
	@Column(length = 13)
	private String mobile;

	
	@OneToMany(mappedBy = "customer",cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
     
	
	@ManyToMany
    @JoinTable(name = "customer_trips", joinColumns = @JoinColumn(name="customer_id",nullable = false),inverseJoinColumns = @JoinColumn(name="trip_id",nullable = false))
    private Set<Trip> trips = new HashSet<>();
	
	public void addBookings(Booking b)	
	{
		this.bookings.add(b);
		b.setCustomer(this);
	}
	
	public void removeBookings(Booking b) {
		this.bookings.remove(b);
		b.setCustomer(null);
	}
	
	public Customer(String name,String email,String password,String mobile) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile; 
	}
	
	
}
