package com.alphadev.AlphaHotel.service.api;

import java.util.List;
import java.util.Optional;

import com.alphadev.AlphaHotel.model.Users;

public interface UserService {
	

	List<Users> getAllUsers();
	public Users findByUserName(String username);
	Users getLoggedInUserProfile();
	Users getUserById(String userId);
	void delete(String userId);
	

}
