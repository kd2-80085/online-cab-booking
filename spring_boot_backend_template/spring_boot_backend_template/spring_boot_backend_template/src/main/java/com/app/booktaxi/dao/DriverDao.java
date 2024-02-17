package com.app.booktaxi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Driver;

public interface DriverDao extends JpaRepository<Driver, Long> {

	Optional<Driver> findByEmail(String email);

}
