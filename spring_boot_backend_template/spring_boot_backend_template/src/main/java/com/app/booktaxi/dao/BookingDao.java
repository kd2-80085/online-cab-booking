package com.app.booktaxi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Customer;

public interface BookingDao extends JpaRepository<Booking, Long>{

	//Optional<List<Booking>> findByCustomer(Pageable pageable);
	Optional<List<Booking>> findAllByCustomer(Customer cust);

	Optional<List<Booking>> findByCustomer(Customer cust, Pageable pageable);

}
