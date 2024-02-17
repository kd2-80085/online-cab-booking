package com.app.booktaxi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * owner
        id       name      email       password        mob.      is_driver
 */

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cars","drivers"},callSuper = true)
public class Owner extends BaseEntity{
	
	@Column(length = 30)
	private String firstName;
	
	@Column(length = 30)
	private String lastName;
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 80)
	private String password;
	
	@Column(name = "mobile",length = 10)
	private String mobile;
	
	@Lob
	private byte[] image;
	
	@Column(length = 15)
	private String status;
  
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Car> cars = new ArrayList<>();
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Driver> drivers = new ArrayList<Driver>();
	
	@Column(length = 15)
	private String serviceStatus;
	
	@Column(name = "is_driver")
	private boolean isDriver;
	
	public void addCar(Car c) {
		this.cars.add(c);
		c.setOwner(this);
	}
	
	public void removeCar (Car c) {
		this.cars.remove(c);
		c.setOwner(null);
	}
  
	public boolean getIsDriver() {
		return this.isDriver;
	}
	
	public void setIsDriver(boolean isDriver) {
		this.isDriver = isDriver;
	}
	
	public void addDriver(Driver d) {
		this.drivers.add(d);
		d.setOwner(this);
	}
	
	public void removeDriver(Driver d) {
		this.drivers.remove(d);
		d.setOwner(null);
	}
}
