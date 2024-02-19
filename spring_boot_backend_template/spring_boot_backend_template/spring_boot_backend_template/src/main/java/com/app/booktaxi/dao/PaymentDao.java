package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {

	Payment findByBooking(Booking booking);


}
