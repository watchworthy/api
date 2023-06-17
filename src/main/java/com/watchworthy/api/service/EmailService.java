package com.watchworthy.api.service;

import com.watchworthy.api.model.EmailType;

import java.io.IOException;

public interface EmailService {
    String sendEmail(String recipientEmail,String recipientName, EmailType type, String var) throws IOException;
}
