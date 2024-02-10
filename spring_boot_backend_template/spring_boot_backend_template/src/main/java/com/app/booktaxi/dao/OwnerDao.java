package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Owner;


public interface OwnerDao  extends JpaRepository<Owner,Long>{


}
