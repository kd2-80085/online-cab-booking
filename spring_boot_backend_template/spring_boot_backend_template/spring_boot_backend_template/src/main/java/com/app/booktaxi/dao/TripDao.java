package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Trip;

public interface TripDao extends JpaRepository<Trip, Long>{

}
