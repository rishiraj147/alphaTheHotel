package com.alphadev.AlphaHotel.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
	
	private String roomType;
	private BigDecimal roomPrice;
	private String roomPhotoUrl;
	private String roomDescription;
	private List<BookingDto> bookings=new ArrayList<>();

}
