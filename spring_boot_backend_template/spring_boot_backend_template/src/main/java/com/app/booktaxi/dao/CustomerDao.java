package com.app.booktaxi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmailAndPassword(String email, String password);

	List<Customer> findAllById(Long customerId);

	Optional<Customer> getByEmail(String email);


}
