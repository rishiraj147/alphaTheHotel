package com.alphadev.AlphaHotel.service.api;

import java.util.List;

import com.alphadev.AlphaHotel.model.User;

public interface UserService {
	
	void register(User loginRequest);

	List<User> getAllUsers();
	

}
