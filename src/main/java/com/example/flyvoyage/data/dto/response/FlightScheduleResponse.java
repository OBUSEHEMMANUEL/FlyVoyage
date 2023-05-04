package com.example.flyvoyage.data.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class FlightScheduleResponse {
    private List<Object> prices ;
    private List<String> origins ;
    private List<String> destinations ;
}
