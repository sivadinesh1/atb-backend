package com.squapl.sa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.squapl.sa.service.serviceimpl.UserSecurityService;

@Component
public class MyScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(UserSecurityService.class);
	
    private static final SimpleDateFormat dateFormat = 
        new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    //commented to stop test
    //@Scheduled(fixedRate = 10000)
    public void sendMailToCustomers() {

        logger.debug("sendMailToCustomers Job ran at " 
            + dateFormat.format(new Date()));

    }
}