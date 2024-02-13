package com.app.booktaxi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Admin;
import com.app.booktaxi.entity.Payment;

public interface AdminDao extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmail(String email);

}
