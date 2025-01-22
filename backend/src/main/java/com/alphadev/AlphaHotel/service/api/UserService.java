package com.alphadev.AlphaHotel.service.api;

import java.util.List;
import java.util.Optional;

import com.alphadev.AlphaHotel.dto.Response;
import com.alphadev.AlphaHotel.dto.UserDto;
import com.alphadev.AlphaHotel.model.Users;

public interface UserService {
	

	Response getAllUsers();
	Response findByUserName(String username);
	Response getLoggedInUserProfile();
	Response getUserById(String userId);
	Response delete(String userId);
	Response getUserBookingHistory(String userId);
	

}
