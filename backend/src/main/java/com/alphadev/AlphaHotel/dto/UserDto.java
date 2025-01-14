package com.alphadev.AlphaHotel.dto;

import java.util.ArrayList;
import java.util.List;

import com.alphadev.AlphaHotel.model.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	    private Long id;
	    private String email;
	    private String name;
	    private String phoneNumber;
	    private String role;
	    private List<BookingDto> booking =new ArrayList<>();
	    
	    public UserDto(Long id, String name, String email) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	    }

}
