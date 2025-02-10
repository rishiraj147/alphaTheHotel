package com.alphadev.AlphaHotel.service.impl;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alphadev.AlphaHotel.dto.BookingDto;
import com.alphadev.AlphaHotel.dto.Response;
import com.alphadev.AlphaHotel.exception.CustomException;
import com.alphadev.AlphaHotel.model.Booking;
import com.alphadev.AlphaHotel.model.Room;
import com.alphadev.AlphaHotel.model.Users;
import com.alphadev.AlphaHotel.repository.BookingRepository;
import com.alphadev.AlphaHotel.repository.RoomRepository;
import com.alphadev.AlphaHotel.repository.UserRepository;
import com.alphadev.AlphaHotel.service.api.BookingService;
import com.alphadev.AlphaHotel.service.api.RoomService;
import com.alphadev.AlphaHotel.util.Utils;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    

	@Override
	public Response saveBooking(Long roomId, Long userId, Booking bookingRequest) {
		// TODO Auto-generated method stub
		 Response response = new Response();

	        try {
	            if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
	                throw new IllegalArgumentException("Check in date must come after check out date");
	            }
	            Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomException("Room Not Found"));
	            Users user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Not Found"));

	            List<Booking> existingBookings = room.getBookings();

	            if (!roomIsAvailable(bookingRequest, existingBookings)) {
	                throw new CustomException("Room not Available for selected date range");
	            }

	            bookingRequest.setRoom(room);
	            bookingRequest.setUser(user);
	            String bookingConfirmationCode = Utils.generateRandomConfirmationCode(10);
	            bookingRequest.setBookingConfirmationCode(bookingConfirmationCode);
	            bookingRepository.save(bookingRequest);
	            response.setStatusCode(200);
	            response.setMessage("successful");
	            response.setBookingConfirmationCode(bookingConfirmationCode);

	        } catch (CustomException e) {
	            response.setStatusCode(404);
	            response.setMessage(e.getMessage());

	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error Saving a booking: " + e.getMessage());

	        }
	        return response;
	}

	@Override
	public Response getAllBookings() {
		// TODO Auto-generated method stub
		Response response = new Response();

        try {
            List<Booking> bookingList = bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<BookingDto> bookingDTOList = Utils.mapBookingListEntityToBookingListDTO(bookingList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBookingList(bookingDTOList);

        } catch (CustomException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Getting all bookings: " + e.getMessage());

        }
        return response;
	}

	@Override
	public Response findBookingByConfirmationCode(String confirmationCode) {
		// TODO Auto-generated method stub
		 Response response = new Response();

	        try {
	            Booking booking = bookingRepository.findByBookingConfirmationCode(confirmationCode).orElseThrow(() -> new CustomException("Booking Not Found"));
	            BookingDto bookingDTO = Utils.mapBookingEntityToBookingDTOPlusBookedRooms(booking, true);
	            response.setStatusCode(200);
	            response.setMessage("successful");
	            response.setBooking(bookingDTO);

	        } catch (CustomException e) {
	            response.setStatusCode(404);
	            response.setMessage(e.getMessage());

	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error Finding a booking: " + e.getMessage());

	        }
	        return response;
	}

	@Override
	public Response cancelBooking(Long bookingId) {
		// TODO Auto-generated method stub
		Response response = new Response();

        try {
            bookingRepository.findById(bookingId).orElseThrow(() -> new CustomException("Booking Does Not Exist"));
            bookingRepository.deleteById(bookingId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (CustomException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Cancelling a booking: " + e.getMessage());

        }
        return response;
	}
	
	
	
    private boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {

        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
    }

	

}
