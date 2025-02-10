package com.alphadev.AlphaHotel.service.api;

import com.alphadev.AlphaHotel.dto.Response;
import com.alphadev.AlphaHotel.model.Booking;

public interface BookingService {

	Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

	Response getAllBookings();

	Response findBookingByConfirmationCode(String confirmationCode);

	Response cancelBooking(Long bookingId);

}
