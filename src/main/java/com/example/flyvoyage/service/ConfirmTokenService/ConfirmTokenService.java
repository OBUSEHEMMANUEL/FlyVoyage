package com.example.flyvoyage.service.ConfirmTokenService;

import com.example.flyvoyage.data.model.ConfirmToken;

import java.util.Optional;

public interface ConfirmTokenService {
    void saveConfirmationToken(ConfirmToken confirmationToken);
    Optional<ConfirmToken> getConfirmationToken(String token);
    void deleteExpiredToken();
    void setConfirmed(String token);
}