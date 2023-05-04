package com.example.flyvoyage.data.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record LoginRequest(String emailAddress,String password) {

}
