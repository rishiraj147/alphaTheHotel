package com.alphadev.AlphaHotel.service.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alphadev.AlphaHotel.dto.Response;

public interface RoomService {

	Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String roomDescription);

	Response getAllRooms();

	List<String> getAllRoomTypes();

	Response getRoomById(Long roomId);

	Response getAllAvailableRooms();

	Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

	Response updateRoom(Long roomId, String roomDescription, String roomType, BigDecimal roomPrice,
			MultipartFile photo);

	Response deleteRoom(Long roomId);

}
