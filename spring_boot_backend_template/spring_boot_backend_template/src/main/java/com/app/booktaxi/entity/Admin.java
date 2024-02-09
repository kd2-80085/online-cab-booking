package com.app.booktaxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * admin       
id       name     email     password     mob. 
 */

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseEntity {

	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String email;
	
	@Column(length = 12)
	private String password;
	
	@Column(length = 13)
	private String mobile;

	@Override
	public String toString() {
		return "Admin [id=" +getId()+ ", name=" + name + ", email=" + email + ", password=" + password + ", mobile=" + mobile + "]";
	}

	
}
