package com.example.flyvoyage.data.dto.request;

public record FlightScheduleRequest(String destination,String origin,String tripType,
                                    String numberOfPassenger,
                                    String departureDateTime,String returnDateType) {

}
