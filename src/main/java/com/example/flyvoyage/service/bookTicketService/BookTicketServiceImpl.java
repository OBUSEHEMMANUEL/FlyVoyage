package com.example.flyvoyage.service.bookTicketService;

import com.example.flyvoyage.data.dto.request.BookingRequest;
import com.example.flyvoyage.data.dto.response.BookingResponse;
import com.example.flyvoyage.data.model.BookTicket;
import com.example.flyvoyage.data.repository.BookTicketRepository;
import com.example.flyvoyage.service.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class BookTicketServiceImpl implements BookTicketService{
    @Autowired
    BookTicketRepository bookTicketRepository;
    @Autowired
    EmailService emailService;

    private static int count;


    public BookingResponse bookFlight(BookingRequest bookingRequest) {


        String ticketNumber= UUID.randomUUID().toString().substring(0,8);

        BookTicket bookTicket = new BookTicket();

        bookTicket.setDob(bookingRequest.dob());
        bookTicket.setFirstName(bookingRequest.firstName());
        bookTicket.setEmailAddress(bookingRequest.emailAddress());
        bookTicket.setLastName(bookingRequest.lastName());
        bookTicket.setNationality(bookingRequest.nationality());
        bookTicket.setMiddleName(bookingRequest.middleName());
        bookTicket.setTitle(bookingRequest.title());
        bookTicket.setPhoneNumber(bookingRequest.phoneNumber());
        bookTicket.setSeatNumBooked(bookingRequest.seatNumBooked());
        bookTicket.setTicketNUmber(ticketNumber + count);
        bookTicketRepository.save(bookTicket);
        count = count + 1;

        emailService.send(bookingRequest.emailAddress(), ticketEmail(bookingRequest,ticketNumber));
        BookingResponse response = new BookingResponse();
        response.setTicketNumber(ticketNumber);
        response.setMessage("Booked Successfully");
        response.setStatus(HttpStatus.OK);
        return response;

    }
    private String ticketEmail(BookingRequest bookingRequest, String ticketNumber){
        String subject = "Flight Booking Confirmation";
        return subject +
                "\n\nDear " + bookingRequest.lastName() + "," +
                "\n\nThank you for booking your flight with us. Here are your booking details:\n\nFlight Number:" +
                " " + ticketNumber +
                "\nBooked Seat Number: " + bookingRequest.seatNumBooked() +
                "\n\nPlease keep this email as your booking confirmation." +
                "\n\nThank you,\nThe Flight Booking Team";
    }

}
