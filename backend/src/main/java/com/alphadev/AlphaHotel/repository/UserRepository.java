package com.alphadev.AlphaHotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphadev.AlphaHotel.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User> findByUsername(String username);
}
