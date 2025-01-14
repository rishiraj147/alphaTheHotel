package com.alphadev.AlphaHotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alphadev.AlphaHotel.model.User;
import com.alphadev.AlphaHotel.repository.UserRepository;
import com.alphadev.AlphaHotel.service.api.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}


	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	
}
