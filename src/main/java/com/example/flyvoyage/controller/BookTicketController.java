package com.example.flyvoyage.controller;

import com.example.flyvoyage.data.dto.request.BookingRequest;
import com.example.flyvoyage.service.airlineService.exceptionHandler.ApiResponse;
import com.example.flyvoyage.service.bookTicketService.BookTicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookTicketController {
    @Autowired
    BookTicketService bookTicketService;

    @PostMapping("/bookFlight")
    public ResponseEntity<ApiResponse> bookFlight(@RequestBody BookingRequest request, HttpServletRequest httpServletRequest){
        ApiResponse apiResponse =  ApiResponse.builder()
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .data(bookTicketService.bookFlight(request))
                .isSuccessful(true).
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @DeleteMapping("/cancel/{passengerId}/{bookTicketId}")
    public ResponseEntity<ApiResponse> CancelFlight(@PathVariable long passengerId,@PathVariable long bookTicketId , HttpServletRequest httpServletRequest){
        ApiResponse apiResponse =  ApiResponse.builder()
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .data(bookTicketService.cancelFlight(passengerId, bookTicketId))
                .isSuccessful(true).
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
