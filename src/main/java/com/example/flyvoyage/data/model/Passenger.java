package com.example.flyvoyage.data.model;


import jakarta.mail.Message;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.engine.internal.Cascade;
import org.springframework.context.annotation.Lazy;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String firstName;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String lastName;
    @Email(message="This field requires a valid email address")
    private String emailAddress;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String phoneNumber;
    private String password;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isDisabled = true;
    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade={CascadeType.ALL})
    private List<BookTicket> bookTickets = new ArrayList<>();
}
