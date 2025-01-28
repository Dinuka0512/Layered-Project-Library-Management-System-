package edu.ijse.gdse.libarymanagementsystem.util;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Gmail {

    public static void sendEmails(String fromEmail, String toEmail, String subject, String body) {
        // SMTP server settings
        final String username = fromEmail; // Your email
        final String password = "jcct qqyt gzit cofm";       // Your email password or app-specific password
        String host = "smtp.gmail.com";

        // Set properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);

            new Alert(Alert.AlertType.CONFIRMATION,"Email sent successfully to " + toEmail).show();
        } catch (MessagingException e) {
            new Alert(Alert.AlertType.ERROR,"Error while sending email: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }
}
