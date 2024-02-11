package com.app.booktaxi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Driver;

public interface CarDao extends JpaRepository<Car, Long> {


	Optional<List<Car>> findAllByDriver(Driver driver, Pageable pageable);

}
