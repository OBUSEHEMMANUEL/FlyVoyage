package com.example.flyvoyage.service;

import com.example.flyvoyage.data.model.ConfirmToken;

import java.util.Optional;

public interface ConfirmTokenService {
    void saveConfirmationToken(ConfirmToken confirmationToken);
    Optional<ConfirmToken> getConfirmationToken(String token);
    void deleteExpiredToken();
    void setConfirmed(String token);
}