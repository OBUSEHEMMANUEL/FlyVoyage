package com.example.flyvoyage.data.dto.request;

import java.math.BigDecimal;

public record PaymentRequest(String name,String description,BigDecimal amount) {

}
