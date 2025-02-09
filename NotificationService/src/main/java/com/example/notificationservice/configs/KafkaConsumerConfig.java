package com.example.notificationservice.configs;

import com.example.notificationservice.dtos.SendEmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class KafkaConsumerConfig {
    
    private final ObjectMapper objectMapper;
    private final EmailUtil emailUtil;

    public KafkaConsumerConfig(ObjectMapper objectMapper, EmailUtil emailUtil) {
        this.objectMapper = objectMapper;
        this.emailUtil = emailUtil;
    }

    @KafkaListener(topics = "sendEmail", groupId = "notificationGroup")
    public void handleEmailEvent(String message) {
        try {
            SendEmailDto emailDto = objectMapper.readValue(message, SendEmailDto.class);
            System.out.println("Kafka event received: " + emailDto);

            Session session = createEmailSession(emailDto.getSenderEmail(), "your-secure-app-password");
            emailUtil.sendEmail(session, emailDto.getReceiverEmail(), emailDto.getSubject(), emailDto.getBody());

        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse Kafka message: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing email event: " + e.getMessage());
        }
    }

    private Session createEmailSession(String senderEmail, String appPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        };

        return Session.getInstance(props, auth);
    }
}
