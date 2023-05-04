package com.example.flyvoyage.service.paymentService;

import com.example.flyvoyage.data.dto.request.PaymentRequest;
import com.example.flyvoyage.data.dto.response.PaymentResponse;

import java.io.IOException;

public interface PaymentService {
    PaymentResponse payment (PaymentRequest paymentRequest) throws IOException;
}
