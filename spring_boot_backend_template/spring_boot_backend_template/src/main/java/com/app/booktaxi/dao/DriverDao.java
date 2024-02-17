package com.app.booktaxi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Owner;

public interface DriverDao extends JpaRepository<Driver, Long> {

	Optional<Driver> findByEmail(String email);

	List<Driver> findByOwner(Owner owner);

}
