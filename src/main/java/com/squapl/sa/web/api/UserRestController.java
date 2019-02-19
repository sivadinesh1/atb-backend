package com.squapl.sa.web.api;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.squapl.sa.domain.User;
import com.squapl.sa.domain.security.UserRole;
import com.squapl.sa.jparepository.RoleDao;
import com.squapl.sa.service.UserService;
import com.squapl.sa.util.APIResponse;
import com.squapl.sa.util.Helper;
import com.squapl.sa.util.MailClient;



	@RestController
	@RequestMapping("/api")
	public class UserRestController {
		private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
		
		private static final String SALT = "salt"; // Salt should be protected carefully
		
		@Autowired
		UserService userService;
		
		@Autowired
		 private Helper helper;
		
		@Autowired
	    private RoleDao roleDao;
		
		@Autowired
		private MailClient mailclient;
		
		
		@GetMapping(value="/getusers/all",
				produces=MediaType.APPLICATION_JSON_VALUE)
//		@CrossOrigin(origins= "http://localhost:4200")
		public ResponseEntity<Iterable<User>> findall(){
			
			
			Iterable<User> users = userService.findUserList();
			
			return new ResponseEntity<Iterable<User>>(users,HttpStatus.OK);
			
		}
		
		@RequestMapping(
	            value = "/profile/{username}",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	//	@CrossOrigin(origins= "http://localhost:4200")
		
		public ResponseEntity<User> findUser(@PathVariable("username") String username) {
	        User user = userService.findByUsername(username);
	        return new ResponseEntity<User>(user,HttpStatus.OK);	    
		}

		@RequestMapping(
	            value = "/profile/id/{userid}",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
//		@CrossOrigin(origins= "http://localhost:4200")
		
		public ResponseEntity<User> getProfilebyUserId(@PathVariable("userid") String userid) {
	        User user = userService.findUserById(Long.parseLong(userid));
	        return new ResponseEntity<User>(user,HttpStatus.OK);	    
		}
	
	
		

		@RequestMapping(
	            value = "/emailsignup",
	            method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	//	@CrossOrigin(origins= "http://localhost:4200")
		
		
			public ResponseEntity signup( @RequestBody String stringToParse, BindingResult bindingResult ) {
			
			logger.debug("kkkkkkkkkkkkkkkkkkkk:::::::;  " + stringToParse);
		        
			JSONObject result = getGeneric(stringToParse);
			
            String email = (String)result.get("email");
            String password = (String)result.get("password");
            String fullname = (String)result.get("fullname");
            String source = (String)result.get("source");
            
			
			 if (email != null && userService.checkEmailExists(email)) {
				 logger.debug("Email Already Exists..");
				 return new ResponseEntity<>(new APIResponse("DP-EMAIL", HttpStatus.OK, ""), HttpStatus.OK);   
			 }
			 
			 if (fullname!= null && userService.checkUsernameExists(fullname)) {
				 logger.debug("username Already Exists..");
				 return new ResponseEntity<>(new APIResponse("DP-USERNAME", HttpStatus.OK, ""), HttpStatus.OK);   
			 }
			 
			 Set<UserRole> userRoles = new HashSet<>();
			 User user = new User();
			
			 user.setEmail(email);
			 user.setEnabled(true);
			 user.setFirstName(fullname);
			 user.setLastName("");
			 user.setMembersince(new Date());
			 user.setPassword(password);
			 user.setPhone("919999999999");
			 
			 user.setProfileimgurl("");
			 user.setSocialid("");
			 user.setSource(source);
			 
			 user.setUsername(fullname.replaceAll("\\s+",""));

			 
             userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);
        	
          //  userService.save(user);
        		return new ResponseEntity<>(new APIResponse("SUCCESS",HttpStatus.OK, ""), HttpStatus.OK);	 
			
		}
		
		
		
		@RequestMapping(
	            value = "/emailsignin",
	            method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		
		
		public ResponseEntity signin( @RequestBody String stringToParse, BindingResult bindingResult ) {
			
			logger.debug("sign in json:::::::;  " + stringToParse);
		        
			JSONObject result = getGeneric(stringToParse);
			
            String username = (String)result.get("username");
            String password = (String)result.get("password");
            
            String source = (String)result.get("source");
            
			 if (!userService.checkUserExists(username)) {
				 return new ResponseEntity<>(new APIResponse("User not Found",HttpStatus.OK,""), HttpStatus.OK);	 
				 
			 } else {
				 User user = userService.getUser(username);
				 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
				 
				if (!passwordEncoder.matches(password, user.getPassword())) {
					logger.debug("ERROR");
					return new ResponseEntity<>(new APIResponse("Password did not Match",HttpStatus.OK,""), HttpStatus.OK);	 
				} else {
					logger.debug("SSSSS"+ user.getUsername());
					return new ResponseEntity<>(new APIResponse("success",HttpStatus.OK, user.getUsername()), HttpStatus.OK);	 
				}
				 
			 }			
		}
		
		
		
		@RequestMapping(
	            value = "/fbsignup",
	            method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		
		public ResponseEntity fbsignup( @RequestBody String stringToParse, BindingResult bindingResult ) {
			
			logger.info("kkkkkkkkkkkkkkkkkkkk:::::::;  " + stringToParse);
		        
			JSONObject result = getGeneric(stringToParse);
			
			String fullname = (String)result.get("fullname");
            String socialid = (String)result.get("tokenid");
            String source = (String)result.get("source");
            
            if(source!= null && source.equalsIgnoreCase("FB")) {
            		if(userService.checkFBSocialIdExists(socialid)) {
            			logger.info("user already exist >> id >> " + socialid + " >> " + fullname);
            			return new ResponseEntity<>(new APIResponse("success",HttpStatus.OK, ""), HttpStatus.OK);	 	
            		} else {
	        			 Set<UserRole> userRoles = new HashSet<>();
	        			 User user = new User();
	        			
	        			 user.setEmail("");
	        			 user.setEnabled(true);
	        			 user.setFirstName(fullname);
	        			 user.setLastName("");
	        			 user.setMembersince(new Date());
	        			 user.setPassword("");
	        			 user.setPhone("");
	        		
	        			 user.setProfileimgurl("");
	        			 user.setSocialid(socialid);
	        			 user.setSource(source);
	        			 
	        			 String uniqueuname = "";
	        			 
	        			 if(userService.checkUsernameExists(fullname.replaceAll("\\s+",""))) {
	        				 String origuname = fullname.replaceAll("\\s+","");
	        				 uniqueuname = origuname + helper.randomfourdigitnumber();
	        				 
	        			 } else {
	        				 uniqueuname = fullname.replaceAll("\\s+","");
	        			 }
	        			 
	        			 user.setUsername(uniqueuname);

	        			 
	                     userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

	                    userService.createFBUser(user, userRoles);
	                	
	                    logger.info("user successfully created >> id >> " + socialid + " >> " + fullname);
	        		}
            }

            logger.info("gp login ..... token id .." +  socialid + "...source.." + source + "...fullname.." + fullname);
			
            return new ResponseEntity<>(new APIResponse("success",HttpStatus.OK, ""), HttpStatus.OK);	 
		}
		
		@RequestMapping(
	            value = "/gpsignup",
	            method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity gpsignup( @RequestBody String stringToParse, BindingResult bindingResult ) {
			
			logger.info("kkkkkkkkkkkkkkkkkkkk:::::::;  " + stringToParse);
		        
			JSONObject result = getGeneric(stringToParse);
			String fullname = (String)result.get("fullname");
            String socialid = (String)result.get("tokenid");
            String source = (String)result.get("source");

            logger.info("gp login ..... token id .." +  socialid + "...source.." + source + "...fullname.." + fullname);
			
            if(source!= null && source.equalsIgnoreCase("GP")) {
	        		if(userService.checkGPSocialIdExists(socialid)) {
	        			logger.info("user already exist >> id >> " + socialid + " >> " + fullname);
	        			return new ResponseEntity<>(new APIResponse("USER_EXIST",HttpStatus.OK, ""), HttpStatus.OK);	 	
	        		} else {
	        			 Set<UserRole> userRoles = new HashSet<>();
	        			 User user = new User();
	        		
	        			 user.setEmail("");
	        			 user.setEnabled(true);
	        			 user.setFirstName(fullname);
	        			 user.setLastName("");
	        			 user.setMembersince(new Date());
	        			 user.setPassword("");
	        			 user.setPhone("919999999999");
	        			 
	        			 user.setProfileimgurl("");
	        			 user.setSocialid(socialid);
	        			 user.setSource(source);
	        			 
	        			 String uniqueuname = "";
	        			 
	        			 if(userService.checkUsernameExists(fullname.replaceAll("\\s+",""))) {
	        				 String origuname = fullname.replaceAll("\\s+","");
	        				 uniqueuname = origuname + helper.randomfourdigitnumber();
	        				 
	        			 } else {
	        				 uniqueuname = fullname.replaceAll("\\s+","");
	        			 }
	        			 
	        			 user.setUsername(uniqueuname);

	        			 
	                     userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

	                    userService.createGPUser(user, userRoles);
	                    logger.info("user created successfully >> id >> " + socialid + " >> " + fullname);
	                    
	        		}
            }	
            return new ResponseEntity<>(new APIResponse("success",HttpStatus.OK, ""), HttpStatus.OK);	 
		}
		
		
		
		@RequestMapping(
	            value = "/recoverpass",
	            method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		
		
		public ResponseEntity recoverpass( @RequestBody String email, BindingResult bindingResult ) {
			User user = null;
			
			logger.debug("sign in json:::::::;  " + email);
		     try {    
			if (email != null && !userService.checkEmailExists(email)) {
				 logger.debug("Email Does not Exists..");
				 return new ResponseEntity<>(new APIResponse("EMAIL-NF", HttpStatus.OK, ""), HttpStatus.OK);   
			 }
			
			
			user = userService.findByEmail(email);
			
			String newpass = helper.getRandomAlphaNumericString(7);
			
			int cnt = userService.resettemppassword(user.getUserId(), newpass);
			
			if(cnt > 0) {
		    		mailclient.prepareAndSend(email, newpass);
			}
			
		     } catch (Exception e) {
		    	 	logger.debug("Error while processing recover password "+ e.getMessage());
		    		return new ResponseEntity<>(new APIResponse("ERROR", HttpStatus.OK, ""), HttpStatus.OK);
		     }
			
			return new ResponseEntity<>(new APIResponse("SUCCESS", HttpStatus.OK, user.getUsername()), HttpStatus.OK);
						
		}
		
		
		   public JSONObject getGeneric( String stringToParse){
		        JSONParser parser = new JSONParser();
		        JSONObject result = null;
		        JSONArray jsonArray = null;
		        String email = null;
		        
		        try {
		        	jsonArray = (JSONArray) parser.parse(stringToParse);
		            
		            result = (JSONObject)jsonArray.get(0);
		            
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		        return result;
		    } 
		   
//		   *********************** ATB  ****************
			
	}

	
	
//	 Set<UserRole> userRoles = new HashSet<>();
//     userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
//
//    userService.createUser(user, userRoles);
//	
//	userService.save(user);
	
	
	
	
//	 @RequestParam("files") MultipartFile[] uploadfiles