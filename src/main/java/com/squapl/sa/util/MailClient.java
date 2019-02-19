package com.squapl.sa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.squapl.sa.service.serviceimpl.UserRepositoryImpl;

@Service
@EnableAsync
public class MailClient {
	
	private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
    private JavaMailSender mailSender;
 
    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    //1. Login to Gmail. 
    //2. Access the URL as https://www.google.com/settings/security/lesssecureapps 
    //3. Select "Turn on"
    @Async
    public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("atbsportz@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Success - Temporary password request");
            messageHelper.setText(message);
        };
        try {
        	logger.debug("EMAIL......................." );
            mailSender.send(messagePreparator);
            logger.debug("EMAIL......................." );
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
    
    @Async
    public void prepareAndSendWithFormat(String recipient, String subject, String message) {
    	String[] bccemails = {"dnvglmcc@gmail.com", "atbsportz@gmail.com"};
    	//String[] bccemails = { "atbsportz@gmail.com"};
    	
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("dnvglmcc@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setBcc(bccemails);
            messageHelper.setSubject(subject);
            //messageHelper.setText(message);
            messageHelper.setText(message, true);
            
        };
        try {
        	logger.debug("EMAIL......................." );
            mailSender.send(messagePreparator);
            logger.debug("EMAIL......................." );
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
 
}