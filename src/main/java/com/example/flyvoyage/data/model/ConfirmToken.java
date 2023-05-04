package com.example.flyvoyage.data.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class ConfirmToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String token;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "passenger_id",referencedColumnName = "id")
    private Passenger passenger;
}
