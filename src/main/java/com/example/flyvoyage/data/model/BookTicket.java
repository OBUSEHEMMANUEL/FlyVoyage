package com.example.flyvoyage.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

@Data
@Entity

public class BookTicket {
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long  id;
    private String title;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dob;
    private String nationality;
    private String phoneNumber;
    private String emailAddress;
    private String seatNumBooked;
    private String ticketNumber;
}
