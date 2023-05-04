package com.example.flyvoyage.data.repository;


import com.example.flyvoyage.data.model.ConfirmToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken,Long> {
    Optional<ConfirmToken> findByToken(String token);
@Transactional
    void deleteConfirmationTokensByExpiredAtBefore(LocalDateTime now);
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmToken confirmToken " +
            "SET confirmToken.confirmedAt = ?1" +
            " WHERE confirmToken.token =?2")
    void confirmAt(LocalDateTime now, String token);
}
