package com.example.flyvoyage.service;

import com.example.flyvoyage.data.dto.request.ConfirmTokenRequest;
import com.example.flyvoyage.data.dto.request.LoginRequest;
import com.example.flyvoyage.data.dto.request.PassengerRegistrationRequest;
import com.example.flyvoyage.data.dto.response.LoginResponse;
import com.example.flyvoyage.data.dto.response.PassengerRegistrationResponse;
import com.example.flyvoyage.data.model.Passenger;
import org.springframework.stereotype.Service;


public interface PassengerService {
    PassengerRegistrationResponse register(PassengerRegistrationRequest request);
    String generateToken(Passenger passenger);
    String confirmToken(ConfirmTokenRequest confirmationToken);
    LoginResponse login(LoginRequest loginRequest);
}
