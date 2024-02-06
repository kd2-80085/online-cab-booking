package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Admin;

public interface AdminDao extends JpaRepository<Admin, Long> {

}
