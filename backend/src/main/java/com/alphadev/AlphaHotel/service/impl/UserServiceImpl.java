package com.alphadev.AlphaHotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alphadev.AlphaHotel.dto.Response;
import com.alphadev.AlphaHotel.dto.UserDto;
import com.alphadev.AlphaHotel.model.Users;
import com.alphadev.AlphaHotel.repository.UserRepository;
import com.alphadev.AlphaHotel.service.api.UserService;
import com.alphadev.AlphaHotel.util.JwtUtil;
import com.alphadev.AlphaHotel.util.Utils;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Response getAllUsers() {
		// TODO Auto-generated method stub
		Response response = new Response();
		try {
			List<Users> userList = userRepository.findAll();
			List<UserDto> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
			response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);
			
		}
		catch(Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
		}
		return response;
	}
	
	public Response findByUserName(String username) {
		Response response = new Response();
		try {
			Users user=userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
			UserDto userDTO = Utils.mapUserEntityToUserDTO(user);
			response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

		}
		catch(Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
		}
		return response;
	}

	@Override
	public Response getUserById(String userId) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try {
			Users user=userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new UsernameNotFoundException("User Id found with username: " + userId));
			UserDto userDTO = Utils.mapUserEntityToUserDTO(user);
			response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);
		}
		catch(Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
		}
		return response;	
	}

	@Override
	public Response delete(String userId) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try {
			userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new UsernameNotFoundException("User Id found with username: " + userId) );
	        userRepository.deleteById(Long.valueOf(userId));
	        response.setStatusCode(200);
            response.setMessage("successful");
	       }
		catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
		 }
		return response;
	}
	
	public Response getLoggedInUserProfile() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		String userName=jwtUtil.getUsernameFromToken(authentication.getName());
		
		return this.findByUserName(userName);
	}

	@Override
	public Response getUserBookingHistory(String userId) {
		// TODO Auto-generated method stub
		Response response = new Response();
        try {
            Users user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            UserDto userDTO = Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;

	}
}
	
	
