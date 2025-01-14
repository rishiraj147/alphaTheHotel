package com.alphadev.AlphaHotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphadev.AlphaHotel.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{
	List<Booking> findByRoomId(Long roomId);
}
