package com.squapl.sa.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.squapl.sa.util.Helper;
import com.squapl.sa.web.api.UserController;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	@Autowired
	 private Helper helper;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        logger.debug("Pre-flight #@");
        
        Set<String> allowedOrigins = new HashSet<String>(Arrays.asList (helper.getAllowedDomains())); 
        String originHeader = request.getHeader("Origin"); 
        logger.debug("originHeader::" + originHeader);
        
        if(allowedOrigins.contains(originHeader)) {  
        	response.setHeader("Access-Control-Allow-Origin", originHeader);
        } 
        
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {
                chain.doFilter(req, res);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.debug("Pre-flight");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Expose-Headers", "Authorization, X-Foobar");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type," +
                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }


    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
