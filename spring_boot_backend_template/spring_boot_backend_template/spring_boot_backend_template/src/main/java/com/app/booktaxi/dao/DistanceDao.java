package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Distance;

public interface DistanceDao extends JpaRepository<Distance, Long>{

	Distance findByPickupLocationAndDropLocation(String pickUpLocation, String dropLocation);

}
