package com.example.flyvoyage.controller;

import com.example.flyvoyage.data.dto.request.PaymentRequest;
import com.example.flyvoyage.service.airlineService.exceptionHandler.ApiResponse;
import com.example.flyvoyage.service.paymentService.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.ZonedDateTime;
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse> payment(@RequestBody PaymentRequest paymentRequest, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .data(paymentService.payment(paymentRequest))
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
