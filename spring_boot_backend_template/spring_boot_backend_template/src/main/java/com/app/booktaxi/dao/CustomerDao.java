package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	Customer findByEmailAndPassword(String email, String password);

}
