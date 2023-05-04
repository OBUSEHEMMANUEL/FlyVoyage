package com.example.flyvoyage.data.dto.request;

public record BookingRequest(
        String title,String firstName,String lastName,
        String middleName,String dob,String nationality,String phoneNumber,String emailAddress,String seatNumBooked) {
}
