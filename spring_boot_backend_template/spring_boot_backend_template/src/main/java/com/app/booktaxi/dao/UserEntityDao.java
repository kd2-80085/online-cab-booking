package com.app.booktaxi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.UserEntity;

public interface UserEntityDao extends JpaRepository<UserEntity, Long> {
//add a finder method
	Optional<UserEntity> findByEmail(String email);
}
