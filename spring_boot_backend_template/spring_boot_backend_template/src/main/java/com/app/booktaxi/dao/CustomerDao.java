package com.app.booktaxi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmailAndPassword(String email, String password);

}
