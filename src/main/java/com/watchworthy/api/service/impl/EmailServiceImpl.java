package com.watchworthy.api.service.impl;

import com.watchworthy.api.model.EmailType;
import com.watchworthy.api.service.EmailService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.io.IOException;
import java.util.*;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Value("${service.client.secrets.sendInBlueKey}")
    private String sendInBlueKey;
    private final ApiKeyAuth apiKey;

    public EmailServiceImpl() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
    }

    @PostConstruct
    public void initializeApiKey() {
        apiKey.setApiKey(sendInBlueKey);
    }
    @Override
    public String sendEmail(String recipientEmail, String recipientName, EmailType type, String var) throws IOException {
        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail("mk51086@ubt-uni.net");
            sender.setName("WatchWorthy");
            List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail(recipientEmail);
            to.setName(recipientName);
            toList.add(to);
            Properties params = new Properties();
            if(type.equals(EmailType.RESET_PASSWORD)){
                params.setProperty("resetLink","http://localhost:3000/reset-password?token="+ var);
            }
            if(type.equals(EmailType.RESET_PASSWORD)){
                params.setProperty("subject", "WatchWorthy - Reset password");
            }else{
                params.setProperty("subject", "Welcome to WatchWorthy");
            }
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setSubject("{{params.subject}}");
            sendSmtpEmail.setParams(params);
            sendSmtpEmail.setTemplateId(type.id);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            return response.toString();
        } catch (Exception e) {
            System.out.println("Exception occurred:- " + e.getMessage());
        }
        return null;
    }


}
