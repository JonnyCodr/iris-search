package com.reporting.iris.irissearch.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Slf4j
@Component
public class EmailUtilImpl implements EmailUtil{

        private final JavaMailSender sender;

    public EmailUtilImpl(JavaMailSender sender) {
        this.sender = sender;
    }


    @Override
    public void sendEmail(String toAddress, String subject, String body) {
        log.info("sending email {} {} {}", toAddress, subject, body);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(body);
        sender.send(message);

    }

    @Override
    public void sendEmailsToList(List<String> toAddressList, String subject, String body) {

    }

//    @Value("#{'${iris.email.list.avera}'.trim().split(',')}")
//    private List<String> emailAddresses;
//

//
//
//    @Override
//    public void sendEmail(String toAddress, String subject, String body) {
//
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        try {
//            helper.setTo(toAddress);
//            helper.setText(body);
//            helper.setSubject(subject);
////            helper.setFrom("RecondoReporter@recondotech.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        sender.send(message);
//
//    }
//
//    @Override
//    public void sendEmailsToList(List<String> toAddressList, String subject, String body) {
//        for (String emailAddress : toAddressList) {
//            sendEmail(emailAddress, subject, body);
//        }
//    }
}
