package com.example.flyvoyage.service.bookTicketService;

import com.example.flyvoyage.data.dto.request.BookingRequest;
import com.example.flyvoyage.data.dto.response.BookingResponse;
import com.example.flyvoyage.data.model.BookTicket;
import com.example.flyvoyage.data.model.Passenger;
import com.example.flyvoyage.data.repository.BookTicketRepository;
import com.example.flyvoyage.exception.BookTicketException;
import com.example.flyvoyage.exception.PassengerException;
import com.example.flyvoyage.service.PassengerService.PassengerService;
import com.example.flyvoyage.service.emailService.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class BookTicketServiceImpl implements BookTicketService{
    @Autowired
   BookTicketRepository bookTicketRepository;

    @Autowired
    PassengerService passengerService;
    @Autowired
    EmailService emailService;

    public BookingResponse bookFlight(BookingRequest bookingRequest) {
          Passenger foundMail = passengerService.getEmailAddress(bookingRequest.emailAddress());
          if (foundMail == null) throw new RuntimeException("Invalid Email");

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
        bookTicket.setTicketNumber(ticketNumber);
        bookTicketRepository.save(bookTicket);
        foundMail.getBookTickets().add(bookTicket);
        passengerService.save(foundMail);


        emailService.send(bookingRequest.emailAddress(), ticketEmail(bookingRequest,ticketNumber));
        BookingResponse response = new BookingResponse();
        response.setTicketNumber(ticketNumber);
        response.setMessage("Booked Successfully");
        response.setStatus(HttpStatus.OK);
        return response;
    }

    @Override
    public BookingResponse cancelFlight(long passengerId,long bookTicketId) {
        Passenger foundPassenger = passengerService.findById(passengerId)
                .orElseThrow(() -> new PassengerException("Id not found"));
        BookTicket foundBookTicket = bookTicketRepository.findById(bookTicketId)
                .orElseThrow(() -> new BookTicketException("Ticket Not Found"));
        foundPassenger.getBookTickets().remove(foundBookTicket);
//        bookTicketRepository.save(foundBookTicket);
        passengerService.save(foundPassenger);
        bookTicketRepository.deleteById(bookTicketId);
        BookingResponse response = new BookingResponse();
        response.setMessage("Deleted Successfully");
        response.setStatus(HttpStatus.OK);
        response.setTicketNumber(foundBookTicket.getTicketNumber());
        return response;
    }

    private String ticketEmail(BookingRequest bookingRequest, String ticketNumber){
        String subject = "Fly Voyage Flight Booking Details";
        return subject +
                "\n\nDear " + bookingRequest.lastName() + "," +
                "\n\nThank you for booking your flight with us. Here are your booking details:\n\nFlight Number:" +
                " " + ticketNumber +
                "\nBooked Seat Number: " + bookingRequest.seatNumBooked() +
                "\n\nPlease keep this email as your booking confirmation." +
                "\n\nThank you,\nThe Flight Booking Team";
    }
    public BookingResponse getAllBookingEntries(){
      var list =  bookTicketRepository.findAll();
        BookingResponse response = new BookingResponse();
        response.setStatus(HttpStatus.OK);
        return response;
    }
    public List<BookTicket> getPassengerBookingEntries(String emailAddress){
     var passenger =   passengerService.getEmailAddress(emailAddress);
    return passenger.getBookTickets();
    }

}
