package com.alphadev.AlphaHotel.util;

import java.util.List;
import java.util.stream.Collectors;

import com.alphadev.AlphaHotel.dto.BookingDto;
import com.alphadev.AlphaHotel.dto.RoomDto;
import com.alphadev.AlphaHotel.dto.UserDto;
import com.alphadev.AlphaHotel.model.Booking;
import com.alphadev.AlphaHotel.model.Room;
import com.alphadev.AlphaHotel.model.Users;

public class Utils {
	
	public static RoomDto mapRoomEntityToRoomDTO(Room room) {
        RoomDto roomDTO = new RoomDto();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());
        return roomDTO;
    }
	
    public static UserDto mapUserEntityToUserDTO(Users user) {
        UserDto userDTO = new UserDto();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRoles());
        return userDTO;
    }

	
    public static BookingDto mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {

        BookingDto bookingDTO = new BookingDto();
        // Map simple fields
        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        if (mapUser) {
            bookingDTO.setUser(Utils.mapUserEntityToUserDTO(booking.getUser()));
        }
        if (booking.getRoom() != null) {
            RoomDto roomDTO = new RoomDto();

            roomDTO.setId(booking.getRoom().getId());
            roomDTO.setRoomType(booking.getRoom().getRoomType());
            roomDTO.setRoomPrice(booking.getRoom().getRoomPrice());
            roomDTO.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
            roomDTO.setRoomDescription(booking.getRoom().getRoomDescription());
            bookingDTO.setRoom(roomDTO);
        }
        return bookingDTO;
    }


	
    public static UserDto mapUserEntityToUserDTOPlusUserBookingsAndRoom(Users user) {
        UserDto userDTO = new UserDto();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRoles());

        if (!user.getBooking().isEmpty()) {
            userDTO.setBooking(user.getBooking().stream().map(booking -> mapBookingEntityToBookingDTOPlusBookedRooms(booking, false)).collect(Collectors.toList()));
        }
        return userDTO;
    }
    
    public static BookingDto mapBookingEntityToBookingDTO(Booking booking) {
        BookingDto bookingDTO = new BookingDto();
        // Map simple fields
        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        return bookingDTO;
    }

    
    public static RoomDto mapRoomEntityToRoomDTOPlusBookings(Room room) {
        RoomDto roomDTO = new RoomDto();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());

        if (room.getBookings() != null) {
            roomDTO.setBookings(room.getBookings().stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList()));
        }
        return roomDTO;
    }


    
    public static List<UserDto> mapUserListEntityToUserListDTO(List<Users> userList) {
        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }
    
    public static List<RoomDto> mapRoomListEntityToRoomListDTO(List<Room> roomList) {
        return roomList.stream().map(Utils::mapRoomEntityToRoomDTO).collect(Collectors.toList());
    }

}
