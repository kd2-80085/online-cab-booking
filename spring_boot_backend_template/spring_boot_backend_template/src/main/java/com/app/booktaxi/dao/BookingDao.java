package com.app.booktaxi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;

public interface BookingDao extends JpaRepository<Booking, Long>{

	Optional<List<Booking>> findByCustomer(Customer cust, Pageable pageable);

	Optional<List<Booking>> findAllByDriver(Driver driver, Pageable pageable);

	List<Booking> findAllByCar(Car car);
	
	Page<Booking> findAll(Pageable pageable);

	
}
