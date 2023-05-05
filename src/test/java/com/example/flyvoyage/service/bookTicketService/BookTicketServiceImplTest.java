package com.example.flyvoyage.service.bookTicketService;

import com.example.flyvoyage.data.dto.request.BookingRequest;
import com.example.flyvoyage.data.dto.request.CancelFightRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookTicketServiceImplTest {
@Autowired
  BookTicketService bookTicketService;
    BookingRequest bookingRequest,bookingRequest2;

    @BeforeEach
public void setUp(){
    bookingRequest = new BookingRequest("MR",
            "Emmanuel","Derek",
            "Marcus","10/04/2023","Canada",
            "0812345122","MarcusManuel@gmail.com","12");

        bookingRequest2 = new BookingRequest("Mrs",
                "Emmanuel","Victory",
                "Edikan","27/05/2023","Canada",
                "0812345122","MarcusManuel@gmail.com","12");
}


    @Test
    void testThatFlightCanBeBooked() {
        assertEquals(HttpStatus.OK,bookTicketService.bookFlight(bookingRequest).getStatus());
    }
    @Test
    void testThatMoreThanOneFlightCanBeBooked(){
        assertEquals(HttpStatus.OK,bookTicketService.bookFlight(bookingRequest2).getStatus());
    }
    @Test
    void testThatBookedFlightCanBeCancelled(){
        assertEquals(HttpStatus.OK,bookTicketService.cancelFlight(1,102).getStatus());
    }
    @Test
    void testThatAllBookingEntriesCanBeSeen(){
        assertEquals(HttpStatus.OK,bookTicketService.getAllBookingEntries().getStatus());
    }
    @Test
    void testThatAPassengerBookingEntriesCanSeen(){
assertEquals(2,bookTicketService.getPassengerBookingEntries("MarcusManuel@gmail.com").size());

    }
}