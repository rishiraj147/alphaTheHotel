package com.alphadev.AlphaHotel.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.alphadev.AlphaHotel.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

	    private Long id;
	    private LocalDate checkInDate;
	    private LocalDate checkOutDate;
	    private int numOfAdults;
	    private int numOfChildren;
	    private int totalNumOfGuest;
	    private String bookingConfirmationCode;
	    private UserDto user;
	    private RoomDto room;
}
