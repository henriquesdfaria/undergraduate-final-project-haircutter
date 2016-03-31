package br.com.haircutter.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by hfaria on 30/03/16.
 */
@Component
public class HaircutterMailSender {

    Logger LOGGER = LoggerFactory.getLogger(HaircutterMailSender.class);

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String emailAddress, String subject, String text) {

        MimeMessagePreparator mmp = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailAddress));
                mimeMessage.setFrom(EMAIL_FROM);
                mimeMessage.setSubject(subject);
                mimeMessage.setText(text);
            }
        };

        try {
            javaMailSender.send(mmp);
            LOGGER.info("E-mail has been sent");
        } catch (MailException me) {
            LOGGER.info("Error sending E-mail");
        }
    }
}
