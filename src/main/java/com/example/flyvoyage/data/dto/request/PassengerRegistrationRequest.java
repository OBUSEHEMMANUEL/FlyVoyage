package com.example.flyvoyage.data.dto.request;


public record PassengerRegistrationRequest
        (String firstName,String lastName,String phoneNumber, String emailAddress,String password) {
}
