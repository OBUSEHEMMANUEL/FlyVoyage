package com.example.flyvoyage.service.bookTicketService;

import com.example.flyvoyage.data.dto.request.BookingRequest;
import com.example.flyvoyage.data.dto.request.CancelFightRequest;
import com.example.flyvoyage.data.dto.response.BookingResponse;
import com.example.flyvoyage.data.model.BookTicket;

import java.util.List;

public interface BookTicketService {
    BookingResponse bookFlight(BookingRequest bookingRequest);
    BookingResponse cancelFlight(long passengerId,long bookTicketId);
    BookingResponse getAllBookingEntries();

    List<BookTicket> getPassengerBookingEntries(String emailAddress);
}
