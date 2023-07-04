package com.example.projekt_60134_kck;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
public class Email {
    public static void Sender() throws Exception {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.protocl", "smtp");

        String emailRecipient = "teststudia23@gmail.com";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("teststudia23@gmail.com", "bwffosuhcusiyvrv");
            }
        });

        Message message = new MimeMessage(session);
        message.setSubject("Nowy kod rabatowy");

        Address addressTo = new InternetAddress(emailRecipient);
        message.setRecipient(Message.RecipientType.TO, addressTo);

        MimeMultipart multipart = new MimeMultipart();

        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File("emailData/test_kod.png"));

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h1> Wiadomosc z aplikacji KCK</h1>", "text/html");

        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachment);

        message.setContent(multipart);

        Transport.send(message);
    }
}
