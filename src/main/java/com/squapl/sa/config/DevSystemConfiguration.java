package com.squapl.sa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile({"dev", "default"})
public class DevSystemConfiguration implements SystemConfiguration{
	
	
	private String categoryimagebaseuploadfolder =  "/Users/sivadineshm/documents/backup/img/categories/";
	
	
	private String baseurl = "http://localhost";
	
	private String[] allowDomain = {"http://localhost:8100", "http://localhost:8000", "http://localhost:80","http://localhost:4200","http://127.0.0.1:4200","http://www.squapl.com", "squapl.com", "www.squapl.com", "http://localhost"};
	
	public String[] getAllowedDomains() {
		return this.allowDomain;
	}
	
	public String getBaseURL() {
		return this.baseurl;
	}
	
	
	
	public String getCategoryImageBaseUploadFolder() {
		return this.categoryimagebaseuploadfolder;
	}
	
	

}
