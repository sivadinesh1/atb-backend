package com.squapl.sa.service.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.squapl.sa.domain.User;
import com.squapl.sa.jparepository.UserDao;
import com.squapl.sa.jparepository.UserDaoCustom;

@Service
public class UserSecurityService implements UserDetailsService {
	

    /** The application logger */
    private static final Logger logger = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserDaoCustom userDaoCustom;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	logger.debug("inside user secutity" + username);
        //User user = userDao.findByUsername(username);
    	User user = userDaoCustom.getUser(username);
    
    	logger.debug("inside after user secutity" + user);
    	
        if (null == user) {
            logger.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        
        
        return user;
    }
}
