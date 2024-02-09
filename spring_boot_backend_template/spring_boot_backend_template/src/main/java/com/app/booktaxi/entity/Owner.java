package com.app.booktaxi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@ToString(exclude = "cars")
public class Owner extends BaseEntity{
	
	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String email;
	
	@Column(length = 12)
	private String password;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(length = 25)
	private String status;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Car> cars = new ArrayList<>();
	
	@Column(length = 25,name = "is_driver")
	private String isDriver;

	
	public void addCar(Car c) {
		this.cars.add(c);
		c.setOwner(this);
	}
	
	public void removeCar (Car c) {
		this.cars.remove(c);
		c.setOwner(null);
	}
}
