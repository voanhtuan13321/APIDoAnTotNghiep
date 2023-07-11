package com.graduation.projectgraduation.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Properties;

/**
 * Xu ly mail.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public class Email {
  private final JavaMailSender mailSender;

  public Email() {
    mailSender = getJavaMailSender();
  }

  /**
   * config javaMailSender.
   */
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    mailSender.setUsername("voanhtuan13321@gmail.com");
    mailSender.setPassword("iomgcrzzynxrhfco");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);

    return mailSender;
  }

  /**
   * Gửi mail thông thường.
   */
  public void sendEmail(String fromEmail, String toEmail, String subject, String body) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(fromEmail);
    mailMessage.setTo(toEmail);
    mailMessage.setSubject(subject);
    mailMessage.setText(body);
    mailSender.send(mailMessage);
  }

  /**
   * Gửi mail dạng html.
   */
  public void sendHtmlMail(String fromEmail, String toEmail, String subject, String body)
      throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(fromEmail);
    helper.setTo(toEmail);
    helper.setSubject(subject);
    helper.setText(body, true);
    mailSender.send(message);
  }

}
