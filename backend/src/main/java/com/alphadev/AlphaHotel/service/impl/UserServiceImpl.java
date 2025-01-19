package com.alphadev.AlphaHotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alphadev.AlphaHotel.model.Users;
import com.alphadev.AlphaHotel.repository.UserRepository;
import com.alphadev.AlphaHotel.service.api.UserService;
import com.alphadev.AlphaHotel.util.JwtUtil;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	public Users findByUserName(String username) {
		 return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
	}
	
	public Users getLoggedInUserProfile() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		String userName=jwtUtil.getUsernameFromToken(authentication.getName());
		return this.findByUserName(userName);
	}

	@Override
	public Users getUserById(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new UsernameNotFoundException("User Id found with username: " + userId) );
	}

	@Override
	public void delete(String userId) {
		// TODO Auto-generated method stub
		try {
			userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new UsernameNotFoundException("User Id found with username: " + userId) );
	        userRepository.deleteById(Long.valueOf(userId));
	       }
		catch (Exception e) {
			System.out.println(e);
		 }
		}
	}
	
	
