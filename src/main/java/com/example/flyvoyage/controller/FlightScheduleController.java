package com.example.flyvoyage.controller;

import com.example.flyvoyage.data.dto.request.FlightScheduleRequest;
import com.example.flyvoyage.data.dto.response.FlightScheduleResponse;
import com.example.flyvoyage.service.airlineService.exceptionHandler.ApiResponse;
import com.example.flyvoyage.service.airlineService.FlightScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/get-flights")
public class FlightScheduleController {


        @Autowired
        private FlightScheduleService flightSchedule;

        @PostMapping
        public ResponseEntity<ApiResponse> searchFlights(@RequestBody FlightScheduleRequest request, HttpServletRequest servletRequest) throws IOException {
            FlightScheduleResponse searchResponse = flightSchedule.getFlightSchedule(request);
            ApiResponse response = ApiResponse.builder()
                    .data(searchResponse)
                    .statusCode(HttpStatus.OK.value())
                    .timeStamp(ZonedDateTime.now())
                    .path(servletRequest.getRequestURI())
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


