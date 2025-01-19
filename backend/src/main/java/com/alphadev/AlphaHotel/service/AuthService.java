package com.alphadev.AlphaHotel.service;

import javax.naming.AuthenticationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.alphadev.AlphaHotel.dto.JwtRequest;
import com.alphadev.AlphaHotel.dto.JwtResponse;
import com.alphadev.AlphaHotel.model.Users;
import com.alphadev.AlphaHotel.repository.UserRepository;
import com.alphadev.AlphaHotel.util.JwtUtil;

@Service
public class AuthService {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public JwtResponse login(JwtRequest jwtRequest) {
//		System.out.println("-------At JwtResponse login----");
		
		//authenticate with Authentication manager
		this.doAuthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		
		// After success authentication we can extract userDetails to generate JWT token 
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtUtil.generateToken(userDetails);
		
		JwtResponse response = JwtResponse.builder().jwtToken(token).build();
	
		return response;
	}
	
	
	private void doAuthenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//		System.out.println(username +" "+ password);
		try {
//			System.out.println("-------At doAuthenticate 1----");
			authenticationManager.authenticate(authenticationToken);
//			System.out.println("-------At doAuthenticate 2----");

		}catch (BadCredentialsException e) {
//			System.out.println("-------At doAuthenticate 3----");
			throw new BadCredentialsException("Invalid Username or Password");
		}
		
	}
	
	
	public void register(Users user) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}

}
