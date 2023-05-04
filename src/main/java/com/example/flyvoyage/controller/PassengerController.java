package com.example.flyvoyage.controller;

import com.example.flyvoyage.data.dto.request.ConfirmTokenRequest;
import com.example.flyvoyage.data.dto.request.LoginRequest;
import com.example.flyvoyage.data.dto.request.PassengerRegistrationRequest;
import com.example.flyvoyage.service.airlineService.exceptionHandler.ApiResponse;
import com.example.flyvoyage.service.PassengerService.PassengerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;
@RestController
@RequestMapping("/api/v1/passenger")
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody PassengerRegistrationRequest request, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .data(passengerService.register(request))
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/confirmToken")
    public ResponseEntity<ApiResponse> confirmPassword(@RequestBody ConfirmTokenRequest confirmationToken, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .data(passengerService.confirmToken(confirmationToken))
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .data(passengerService.login(loginRequest))
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);



    }

}
