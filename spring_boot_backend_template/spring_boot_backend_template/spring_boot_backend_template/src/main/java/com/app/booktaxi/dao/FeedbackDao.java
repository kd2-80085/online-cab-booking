package com.app.booktaxi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Long>{

	Optional<List<Feedback>> findAllByDriver(Driver driver, Pageable pageable);

}
