package com.alphadev.AlphaHotel.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alphadev.AlphaHotel.model.Users;
import com.alphadev.AlphaHotel.service.api.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	 @Autowired
	 private UserService userService;
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public List<Users> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/get-by-id/{userId}")
	public Users getUserById(@PathVariable("userId") String userId) {
		return userService.getUserById(userId);
	}

	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable("userId") String userId) {
		 userService.delete(userId);
	}
	
	@GetMapping("/get-logged-in-profile-info")
	public Users getLoggedInUserProfile() {
		return userService.getLoggedInUserProfile();
	}
	

	

}
