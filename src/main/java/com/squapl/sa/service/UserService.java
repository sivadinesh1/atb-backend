package com.squapl.sa.service;

import java.util.List;
import java.util.Set;


import com.squapl.sa.domain.User;
import com.squapl.sa.domain.security.UserRole;
import com.squapl.sa.util.constants.UserStatus;

public interface UserService {
	User findByUsername(String username);

    User findByEmail(String email);
    
    User getUser(String name);

    boolean checkUserExists(String username, String email);
    boolean checkUserExists(String username);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save (User user);
    
    User createUser(User user, Set<UserRole> userRoles);
    
    User saveUser (User user); 
    
    List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);
    
    void deleteUserById(Long userId);
	 
   	User findUserById(Long clientId);
   	
   	

   	List<User> findByEnabledUser(UserStatus enable);
   	
   	List<User> findByDisabledUser(UserStatus disable);
   	
   	User findUsersByQuery();
	int updateProfileImgUrl(String profileimgurl, Long userid);
	
	
	
	int resettemppassword(Long userId, String newpassword) throws Exception;
	
	boolean checkFBSocialIdExists(String socialid);
	boolean checkGPSocialIdExists(String socialid);
	
	User createFBUser(User user,  Set<UserRole> userRoles);
	User createGPUser(User user,  Set<UserRole> userRoles);
	
}
