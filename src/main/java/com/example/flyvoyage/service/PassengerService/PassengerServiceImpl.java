package com.example.flyvoyage.service;

import com.example.flyvoyage.data.dto.request.ConfirmTokenRequest;
import com.example.flyvoyage.data.dto.request.LoginRequest;
import com.example.flyvoyage.data.dto.request.PassengerRegistrationRequest;
import com.example.flyvoyage.data.dto.response.LoginResponse;
import com.example.flyvoyage.data.dto.response.PassengerRegistrationResponse;
import com.example.flyvoyage.data.model.ConfirmToken;
import com.example.flyvoyage.data.model.Passenger;
import com.example.flyvoyage.data.repository.PassengerRepository;
import com.example.flyvoyage.service.emailService.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepo;
    @Autowired
    ConfirmTokenService confirmTokenService;
    @Autowired
    EmailService emailService;


    @Override
    public PassengerRegistrationResponse register(PassengerRegistrationRequest request) {
        boolean emailExist = passengerRepo.findByEmailAddressIgnoreCase(request.emailAddress()).isPresent();
        if (emailExist) throw new IllegalStateException("Email Address Already Exist");
        var hashed = bcrypt(request.password());

        Passenger passenger = new Passenger();
        passenger.setEmailAddress(request.emailAddress());
        passenger.setFirstName(request.firstName());
        passenger.setPassword(hashed);
        passenger.setLastName(request.lastName());
        passenger.setPhoneNumber(request.phoneNumber());
        passengerRepo.save(passenger);
        String token = generateToken(passenger);

        PassengerRegistrationResponse response = new PassengerRegistrationResponse();
        response.setMessage("Created Successfully");
        response.setStatusCode(HttpStatus.CREATED);
        response.setToken(token);
        emailService.send(request.emailAddress(),buildEmail(request.lastName(),token));
        return response;
    }
    private String bcrypt(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);

    }

    @Override
    public String generateToken(Passenger passenger) {
        StringBuilder tok= new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(9);
            tok.append(num);
        }
        StringBuilder token = new StringBuilder(tok.toString());

        ConfirmToken confirmToken = new ConfirmToken();
        confirmToken.setToken(String.valueOf(token));
        confirmToken.setPassenger(passenger);
        confirmToken.setCreatedAt(LocalDateTime.now());
        confirmToken.setExpiredAt(LocalDateTime.now().plusMinutes(10));
        confirmTokenService.saveConfirmationToken(confirmToken);
        return token.toString();
    }

    @Override
    public String confirmToken(ConfirmTokenRequest confirmToken){
        var token = confirmTokenService.getConfirmationToken(confirmToken.token())
                .orElseThrow(()-> new IllegalStateException("Token does not exist"));

        if (token.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token has expired");
        }
        confirmTokenService.setConfirmed(token.getToken());

        return "confirmed";
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var foundUser = passengerRepo.findByEmailAddressIgnoreCase(loginRequest.emailAddress())
                    .orElseThrow(() -> new RuntimeException("email not found"));

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            var matches = encoder.matches(loginRequest.password(), foundUser.getPassword());
            LoginResponse loginResponse = new LoginResponse();
            if (matches) {
                loginResponse.setStatusCode(HttpStatus.OK);

                loginResponse.setLoggedIn("ACTIVE");
                loginResponse.setMessage("LOGIN SUCCESSFULLY");
            } else {
                loginResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
                loginResponse.setLoggedIn("INACTIVE");
                loginResponse.setMessage("INVALID EMAIL OR PASSWORD");
            }
            return loginResponse;
        }catch (RuntimeException e){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            loginResponse.setLoggedIn("INACTIVE");
            loginResponse.setMessage("ERROR: " + e.getMessage());
            return loginResponse;
        }
    }

    public String buildEmail(String name, String token) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\FlyVoyage\\src\\main\\resources\\email_template.txt "));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("{name}")) {
                    line = line.replace("{name}", name);
                }
                if (line.contains("{token}")) {
                    line = line.replace("{token}", token);
                }
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        System.out.println(content);
        return content.toString();
    }

}
