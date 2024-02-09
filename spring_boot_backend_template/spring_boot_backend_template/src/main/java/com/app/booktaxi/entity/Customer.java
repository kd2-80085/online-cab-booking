package com.app.booktaxi.entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< Updated upstream
=======
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
>>>>>>> Stashed changes
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
<<<<<<< Updated upstream
=======
@ToString(exclude = {"bookings","trips"},callSuper = true)
>>>>>>> Stashed changes
public class Customer extends BaseEntity {
	
	@Column(length = 50)
	private String firstName;
	
	@Column(length = 50)
	private String lastName;
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 80)
	private String password;
	
	@Column(length = 10)
	private String mobile;
	
	@Lob
	private byte[] image;

	@OneToMany(mappedBy = "customer",cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();

<<<<<<< Updated upstream
	public void addBookings(Booking b)	{
=======
	@ManyToMany
    @JoinTable(name = "customer_trips", joinColumns = @JoinColumn(name="customer_id",nullable = false),inverseJoinColumns = @JoinColumn(name="trip_id",nullable = false))
    private Set<Trip> trips = new HashSet<>();
	
	public void addBookings(Booking b)	
	{
>>>>>>> Stashed changes
		this.bookings.add(b);
		b.setCustomer(this);
	}
	
	public void removeBookings(Booking b) {
		this.bookings.remove(b);
		b.setCustomer(null);
	}
	
	public Customer(String firstName,String lastName,String email,String password,String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobile = mobile; 
	}
	
	@Override
	public String toString() {
		return "Customer [id=" +getId()+ ", name=" + name + ", email=" + email + ", password=" + password + ", mobile=" + mobile
				+ ", bookings=" + bookings + "]";
	}
	
	
	
}
