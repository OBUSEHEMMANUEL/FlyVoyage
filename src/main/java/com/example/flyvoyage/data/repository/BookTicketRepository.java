package com.example.flyvoyage.data.repository;


import com.example.flyvoyage.data.model.BookTicket;
import com.example.flyvoyage.data.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookTicketRepository extends JpaRepository<BookTicket, Long> {
    Optional<BookTicket> findByEmailAddressIgnoreCase(String emailAddress);
}
