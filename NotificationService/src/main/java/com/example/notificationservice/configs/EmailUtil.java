package com.example.notificationservice.configs;

import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Configuration
public class EmailUtil {

    /**
     * Sends an HTML email using the provided session details.
     * 
     * @param session  Email session configuration
     * @param toEmail  Recipient email address
     * @param subject  Email subject line
     * @param body     Email body content
     * @throws UnsupportedEncodingException 
     */
    public void sendEmail(Session session, String toEmail, String subject, String body) throws UnsupportedEncodingException {
        try {
            MimeMessage message = createEmailMessage(session, toEmail, subject, body);
            Transport.send(message);
            System.out.println("Email successfully sent to: " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates a properly formatted MIME email message.
     *
     * @param session  Email session configuration
     * @param toEmail  Recipient email address
     * @param subject  Email subject
     * @param body     Email content
     * @return         A fully constructed MimeMessage
     * @throws MessagingException if message construction fails
     * @throws UnsupportedEncodingException 
     */
    private MimeMessage createEmailMessage(Session session, String toEmail, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        message.setHeader("Content-Type", "text/html; charset=UTF-8");
        message.setHeader("format", "flowed");
        message.setHeader("Content-Transfer-Encoding", "8bit");

        message.setFrom(new InternetAddress("no_reply@example.com", "NoReply-Service", StandardCharsets.UTF_8.name()));
        message.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

        message.setSubject(subject, StandardCharsets.UTF_8.name());
        message.setSentDate(new Date());
        message.setContent(body, "text/html; charset=UTF-8");

        return message;
    }
}
