package com.example.flyvoyage.data.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookingResponse {
    private HttpStatus status;
    private String message;
    private String ticketNumber;
}

