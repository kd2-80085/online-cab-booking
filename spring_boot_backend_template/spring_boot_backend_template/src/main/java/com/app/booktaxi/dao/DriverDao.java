package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Driver;

public interface DriverDao extends JpaRepository<Driver, Long> {

}
