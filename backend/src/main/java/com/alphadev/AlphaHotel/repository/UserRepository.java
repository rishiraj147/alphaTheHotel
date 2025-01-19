package com.alphadev.AlphaHotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphadev.AlphaHotel.model.Users;

public interface UserRepository extends JpaRepository<Users,Long>{
	
	Optional<Users> findByUsername(String username);
	Optional<Users> findById(Long Id);
	
	
}
