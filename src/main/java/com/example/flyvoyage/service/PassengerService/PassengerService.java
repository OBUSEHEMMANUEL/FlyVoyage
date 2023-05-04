package com.example.flyvoyage.service.PassengerService;

import com.example.flyvoyage.data.dto.request.ConfirmTokenRequest;
import com.example.flyvoyage.data.dto.request.LoginRequest;
import com.example.flyvoyage.data.dto.request.PassengerRegistrationRequest;
import com.example.flyvoyage.data.dto.response.LoginResponse;
import com.example.flyvoyage.data.dto.response.PassengerRegistrationResponse;
import com.example.flyvoyage.data.model.Passenger;

import java.util.Optional;


public interface PassengerService {
    PassengerRegistrationResponse register(PassengerRegistrationRequest request);

    String confirmToken(ConfirmTokenRequest confirmationToken);
    LoginResponse login(LoginRequest loginRequest);

    Passenger getEmailAddress(String emailAddress);
    Passenger save(Passenger passenger);

    Optional<Passenger> findById(long id);


}
