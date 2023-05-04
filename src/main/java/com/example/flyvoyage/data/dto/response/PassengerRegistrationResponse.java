package com.example.flyvoyage.data.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class PassengerRegistrationResponse {
    private String message;
    private HttpStatus statusCode;
    private String token;
}
