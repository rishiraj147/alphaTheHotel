package com.alphadev.AlphaHotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphadev.AlphaHotel.dto.JwtRequest;
import com.alphadev.AlphaHotel.dto.JwtResponse;
import com.alphadev.AlphaHotel.model.Users;
import com.alphadev.AlphaHotel.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
//		System.out.println("-------At AuthController ----");
		return new ResponseEntity<>(authService.login(jwtRequest),HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public void register(@RequestBody Users user){
		authService.register(user);
	}

}
