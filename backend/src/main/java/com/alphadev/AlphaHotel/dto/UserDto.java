package com.alphadev.AlphaHotel.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alphadev.AlphaHotel.model.Booking;
import com.alphadev.AlphaHotel.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	    private Long id;
	    private String email;
	    private String name;
	    private String phoneNumber;
	    private Set<Role> role;
	    private List<BookingDto> booking =new ArrayList<>();
	    
//	    public UserDto(Long id, String name, String email) {
//	        this.id = id;
//	        this.name = name;
//	        this.email = email;
//	    }

}
