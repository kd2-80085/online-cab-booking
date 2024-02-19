package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "distances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Distance extends BaseEntity{
	
	@Column(length = 60)
	private String pickupLocation;
	
	@Column(length = 60)
	private String dropLocation;
	
	private float distance;

}
