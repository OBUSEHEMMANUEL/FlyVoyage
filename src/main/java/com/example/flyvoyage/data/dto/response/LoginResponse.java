package com.example.flyvoyage.data.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Setter
@Getter
public class LoginResponse {
    private  String message;
    private HttpStatus statusCode;
    private String LoggedIn;
}
