package com.app.booktaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Payment2;
public interface Payment2Dao  extends JpaRepository<Payment2, Long>
{
	Payment2 findByBooking(Booking booking);
	@Query("SELECT p FROM Payment2 p WHERE p.razor_order_id = :razorOrderId")
    Payment2 findByRazorOrderId(@Param("razorOrderId") String razorOrderId);


}

