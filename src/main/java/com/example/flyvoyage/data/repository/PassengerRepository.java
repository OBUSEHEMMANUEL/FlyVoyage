package com.example.flyvoyage.data.repository;

import com.example.flyvoyage.data.model.Passenger;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByEmailAddressIgnoreCase(String emailAddress);
    @Transactional
    @Modifying
    @Query("UPDATE  Passenger  passenger " +
            "SET passenger.isDisabled = false" +
            " WHERE passenger.emailAddress=?1")
    void enable(String emailAddress);

}
