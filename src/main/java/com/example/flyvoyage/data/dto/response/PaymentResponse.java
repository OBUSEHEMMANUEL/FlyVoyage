package com.example.flyvoyage.data.dto.response;

import com.example.flyvoyage.data.model.RecipientData;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PaymentResponse {
    private HttpStatus statusCode;
    private String message;
    private RecipientData data;
}