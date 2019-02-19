package com.squapl.sa.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.squapl.sa.config.SystemConfiguration;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;
import com.rosaloves.bitlyj.Url;



@Component
public class Helper {
	private static final Logger logger = LoggerFactory.getLogger(Helper.class);
	
	
	
	@Autowired
	private  SystemConfiguration systemconfiguration;
	

	
	public  String[] getAllowedDomains() {
		return systemconfiguration.getAllowedDomains(); 
	}
	
	
	
	public  String getBaseURL() {
		return systemconfiguration.getBaseURL(); 
	}
	

	
	public String getCategoryUploadFolder() {
		return systemconfiguration.getCategoryImageBaseUploadFolder();
	}
	
	
	
	  public  List<String> getName(String name, List<String> list) {
		  //List<String> usersList = clientService.findActiveUsers();

		  String[] usersArr = new String[list.size()];
		  usersArr = list.toArray(usersArr);
		  
		    List<String> returnMatchName = new ArrayList<String>();
		    String [] data =usersArr;    
		    for (String string : data) {
		        if (string.toUpperCase().indexOf(name.toUpperCase())!= -1) {
		        returnMatchName.add(string);
		        }
		    }
		    
	    return returnMatchName;
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
	  

	  
	  public JSONObject getJsonObj( String stringToParse){
	        JSONParser parser = new JSONParser();
	        JSONObject result = null;
	        
	        try {
	        	result =   (JSONObject)parser.parse(stringToParse);
	            
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return result;
	    } 

    
    public String getJsonArrtoString(JSONArray jsonarr) {
	    ArrayList arrayList = new ArrayList(jsonarr.size());

    	for(int i=0;i < jsonarr.size();i++){
    		arrayList.add(jsonarr.get(i));
		}

		String commaSeparatedValue = String.join(",", arrayList);
		
		return commaSeparatedValue;
	}
    
    public long getJsonArrtoInt(JSONArray jsonarr) {
		return (long)jsonarr.get(0);
  
    }
	  
	  public void deleteFilesinDir(File dir) {
		  
		  for(File file: dir.listFiles()) 
			    if (!file.isDirectory()) 
			        file.delete();
	  }
	  
	  
	    public static List getAllFilse(File curDir, String articledirectory) {
	        List filelist = new ArrayList();
	        File[] filesList = curDir.listFiles();
	        logger.debug("getAllFilse Inside : "+filesList.toString());
	        for(File f : filesList){
	            if(f.isDirectory())
	                logger.debug("getAllFilse getfilename before change : "+f.getName());
	            if(f.isFile()){                
	                //filelist.add(curDir+ System.getProperty("file.separator") + f.getName());
	            	
	            	articledirectory = articledirectory.substring(articledirectory.indexOf("/img"),articledirectory.length());
	            	
	                logger.debug("getAllFilse get filename after change : "+ articledirectory);	
	                
	                filelist.add(new ImageMeta(f.getName(),articledirectory+ System.getProperty("file.separator") +f.getName()));
	                
	            
	            }
	        }
	        logger.debug("Exit getAllFilse exit : "+filelist.toString());
	        return filelist;
	    }
	    
	    public static String getBitly(String baseurl, String idname, String id) {
	        
	        StringBuilder bitlyurlquery = new StringBuilder();
	        
	        String rname = idname.toLowerCase().replaceAll("[^a-zA-Z0-9]+", " ");
	                rname = rname.replaceAll(" ", "-");
	        
	        bitlyurlquery.append(baseurl);
	        bitlyurlquery.append("/").append(rname);
	        bitlyurlquery.append("-").append(id);
	            
	        
	        try {
	            
	        Url url = as("o_55g39fkks", "R_db5224b6c8434b949ee6534ce8730c29").call(shorten(bitlyurlquery.toString()));
	            return url.getShortUrl();
	        } catch (Exception e) {
	            logger.debug("Error in getBitly " + e.getMessage());
	            return "";
	        }  
	         
	         
	    }
	    

		public static String getRandomAlphaNumericString(int length) 
		{
		   String randomStr = UUID.randomUUID().toString();
		   
		   while(randomStr.length() < length) {
		       randomStr += UUID.randomUUID().toString();
		   }
		   return randomStr.replace("-", "").substring(0, length);
		}
		
		
		public String randomfourdigitnumber() {
			String val = ""+((int)(Math.random()*9000)+1000);
			return val;
		}
		

		public static String dateConversion(String dt) {

	 	    Instant timestamp = Instant.parse(dt);
	 	    ZonedDateTime isttime = timestamp.atZone(ZoneId.of("Asia/Kolkata"));
	 	    
	 	
	 	    logger.debug(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(isttime));
	 	    return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(isttime);
	 	}
		
		
	 	
		
	  
}
