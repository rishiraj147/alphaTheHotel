package com.alphadev.AlphaHotel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphadev.AlphaHotel.model.User;
import com.alphadev.AlphaHotel.service.api.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	 @Autowired
	 private UserService userService;
	
	
	@PostMapping("/register")
	public void register(@RequestBody User user){
		 userService.register(user);
	
	}
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

}
