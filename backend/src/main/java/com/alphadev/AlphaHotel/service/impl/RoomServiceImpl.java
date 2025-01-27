package com.alphadev.AlphaHotel.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alphadev.AlphaHotel.dto.Response;
import com.alphadev.AlphaHotel.dto.RoomDto;
import com.alphadev.AlphaHotel.exception.CustomException;
import com.alphadev.AlphaHotel.model.Room;
import com.alphadev.AlphaHotel.repository.RoomRepository;
import com.alphadev.AlphaHotel.service.api.RoomService;
import com.alphadev.AlphaHotel.util.Utils;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
		// TODO Auto-generated method stub
		Response response = new Response();
		
		try {
            Room room = new Room();
            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDescription(description);
            Room savedRoom = roomRepository.save(room);
            RoomDto roomDTO = Utils.mapRoomEntityToRoomDTO(savedRoom);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
	}

	@Override
	public Response getAllRooms() {
		// TODO Auto-generated method stub
		Response response = new Response();
		
		try {
            List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<RoomDto> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoomList(roomDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
	}

	@Override
	public List<String> getAllRoomTypes() {
		// TODO Auto-generated method stub
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public Response getRoomById(Long roomId) {
		// TODO Auto-generated method stub
		Response response = new Response();

        try {
            Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomException("Room Not Found"));
            RoomDto roomDTO = Utils.mapRoomEntityToRoomDTOPlusBookings(room);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        } catch (CustomException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
	}

}
