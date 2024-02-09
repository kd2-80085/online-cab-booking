package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Long>{

}
