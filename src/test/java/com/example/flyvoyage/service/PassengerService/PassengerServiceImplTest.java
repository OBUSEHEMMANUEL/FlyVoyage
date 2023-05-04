package com.example.flyvoyage.service.PassengerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.flyvoyage.data.dto.request.ConfirmTokenRequest;
import com.example.flyvoyage.data.dto.request.LoginRequest;
import com.example.flyvoyage.data.dto.request.PassengerRegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PassengerServiceImplTest {
    @Autowired
    PassengerService passengerService;
    PassengerRegistrationRequest passenger;

    ConfirmTokenRequest tokenRequest;


    @BeforeEach
    void setUp() {
        passenger = new PassengerRegistrationRequest("Derek","Bolaji",
                "07023453617","obusehemmanuel208@gmail.com","7654321");
    }

    @Test
    void testThatPassengerCanRegister() {
        assertEquals(HttpStatus.CREATED,passengerService.register(passenger).getStatusCode());
    }

    @Test
    void testThatPassengerCanLogin(){
        LoginRequest loginRequest = new LoginRequest("obusehemmanuel208@gmail.com","7654321");

        assertEquals(HttpStatus.OK,passengerService.login(loginRequest).getStatusCode());
    }

    @Test
    void testThatPassengerCanConfirmPasswordWhenANewRegistrationIsAdd(){
        PassengerRegistrationRequest   passenger1 = new PassengerRegistrationRequest("Marcus",
                "Manuel","07023453617","MarcusManuel208@gmail.com","54321");

        var response =  passengerService.register(passenger1);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        tokenRequest = new ConfirmTokenRequest(response.getToken(),"MarcusManuel208@gmail.com");

        assertEquals( "confirmed",passengerService.confirmToken(tokenRequest));

    }


}