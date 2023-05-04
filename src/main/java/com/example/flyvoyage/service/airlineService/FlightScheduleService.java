package com.example.flyvoyage.service.airlineService;

import com.example.flyvoyage.data.dto.request.FlightScheduleRequest;
import com.example.flyvoyage.data.dto.response.FlightScheduleResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface FlightScheduleService {

    FlightScheduleResponse getFlightSchedule(FlightScheduleRequest searchRequest) throws IOException;
}
