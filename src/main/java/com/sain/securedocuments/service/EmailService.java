package com.sain.securedocuments.service;

public interface EmailService {
    void sendNewAccountEmail(String name,String email,String token);
    void sendNewPasswordResetEmail(String name,String email,String token);
}
