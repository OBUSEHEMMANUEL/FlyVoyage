package com.example.flyvoyage.data.dto.request;

public record CancelFightRequest(long passengerId, long bookTicketId, String ticketNumber) {
}
