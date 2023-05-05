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
    PassengerRegistrationRequest   passenger1;
    ConfirmTokenRequest tokenRequest;
    ConfirmTokenRequest tokenRequest1;


    @BeforeEach
    void setUp() {
        passenger = new PassengerRegistrationRequest("Derek","Bolaji",
                "07023453617","obusehemmanuel@gmail.com","7654321");

         passenger1 = new PassengerRegistrationRequest("Marcus", "Manuel",
                 "07023453617","MarcusManuel@gmail.com","54321");
    }

    @Test
    void testThatPassengerCanRegister() {
        assertEquals(HttpStatus.CREATED,passengerService.register(passenger).getStatusCode());
    }
    @Test
    void testThatPassengerCanConfirmTokenWhenANewRegistrationIsAdd(){
        var response =  passengerService.register(passenger1);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        tokenRequest1 = new ConfirmTokenRequest(response.getToken(),"MarcusManuel@gmail.com");
        assertEquals( "confirmed",passengerService.confirmToken(tokenRequest1));
    }

    @Test
    void testThatPassengerCanLogin(){
        LoginRequest loginRequest = new LoginRequest("MarcusManuel@gmail.com","54321");

        assertEquals(HttpStatus.OK,passengerService.login(loginRequest).getStatusCode());
    }
    @Test
    void ThatPassengerCannotLoginWithWrongPassword(){
        LoginRequest loginRequest = new LoginRequest("MarcusManuel@gmail.com","12345");
        assertEquals(HttpStatus.UNAUTHORIZED,passengerService.login(loginRequest).getStatusCode());
    }
    @Test
    void ThatPassengerCannotLoginWithWrongEmail(){
        LoginRequest loginRequest = new LoginRequest("Marcus@gmail.com","54321");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,passengerService.login(loginRequest).getStatusCode());
    }
    @Test
    void testThatPassengerCannotLoginWhenTokenISnotConfirmed() {
        LoginRequest loginRequest = new LoginRequest("obusehemmanuel@gmail.com", "1234567");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, passengerService.login(loginRequest).getStatusCode());
    }
}