package com.exercisetracker.services;

import javax.mail.internet.MimeMessage;

import com.exercisetracker.domain.Program;
import com.exercisetracker.domain.User;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendProgramConfirmationEmail(Program obj);

    void sendEmail(SimpleMailMessage msg);

    void sendProgramConfirmationHtmlEmail(Program obj);

    void sendHtmlEmail(MimeMessage msg);
    
    void sendNewPasswordEmail(User cliente,String newPass);
}