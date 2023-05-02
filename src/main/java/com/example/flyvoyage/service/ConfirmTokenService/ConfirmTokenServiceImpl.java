package com.example.flyvoyage.service;

import com.example.flyvoyage.data.model.ConfirmToken;
import com.example.flyvoyage.data.repository.ConfirmTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmTokenServiceImpl implements ConfirmTokenService{
    @Autowired
    private ConfirmTokenRepository confirmTokenRepository;

@Override
    public void saveConfirmationToken(ConfirmToken confirmationToken){
        confirmTokenRepository.save(confirmationToken);
    }
   @Override
    public Optional<ConfirmToken> getConfirmationToken(String token){
        return confirmTokenRepository.findByToken(token);
    }
    @Override
    @Scheduled(cron = "0 */5 * * * *")
    public  void deleteExpiredToken(){
        confirmTokenRepository.deleteConfirmationTokensByExpiredAtBefore(LocalDateTime.now());
    }
    @Override
    public  void setConfirmed(String token ){
        confirmTokenRepository.confirmAt(LocalDateTime.now(),token);
    }
}