package com.app.booktaxi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Car;

public interface CarDao extends JpaRepository<Car, Long> {

	List<Car> findByLocation(String location);

}
