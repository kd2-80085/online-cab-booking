package com.app.booktaxi.dao;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Feedback;
import com.app.booktaxi.entity.Payment;

public interface FeedbackDao extends JpaRepository<Feedback, Long>{

	Optional<List<Feedback>> findByDriver(Driver driver, Pageable pageable);

=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Long>{

>>>>>>> 6c4a7478be2bb938ad9f6856eaf78618625244e1
}
