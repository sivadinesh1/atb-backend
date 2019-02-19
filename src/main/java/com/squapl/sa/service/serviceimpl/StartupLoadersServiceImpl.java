package com.squapl.sa.service.serviceimpl;


import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.squapl.sa.service.StartupLoadersService;

@Component
public class StartupLoadersServiceImpl implements StartupLoadersService {
	private static final Logger logger = LoggerFactory.getLogger(StartupLoadersServiceImpl.class);
	
    @Autowired
    ElasticsearchOperations operations;
    
    @Autowired
    ElasticsearchTemplate template;
    
   
    
    @PostConstruct
    @Transactional
    public void startupLoadAll(){
    	try {
    	
            
            //template or operations can be used 

	        logger.debug("Loading Started");
	        logger.debug("Loading Started>");
	        
	    
	 		
	        logger.debug("Loading Completed");
	        logger.info("Loading Completed>");
    	} catch (Exception e) {
    		logger.debug("Error in Method >> startupLoadAll >> " + e.getMessage());
    	}
    }

}
