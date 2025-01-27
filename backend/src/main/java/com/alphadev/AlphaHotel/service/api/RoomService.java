package com.alphadev.AlphaHotel.service.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alphadev.AlphaHotel.dto.Response;

public interface RoomService {

	Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String roomDescription);

	Response getAllRooms();

	List<String> getAllRoomTypes();

	Response getRoomById(Long roomId);

}
