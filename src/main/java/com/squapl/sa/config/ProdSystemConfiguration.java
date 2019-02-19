package com.squapl.sa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdSystemConfiguration implements SystemConfiguration {
	private String campaignimageuploadfolder = 		"/var/www/html/img/campaign/";
	private String articleimagebaseuploadfolder =  	"/var/www/html/img/articles/";
	private String pictureimagebaseuploadfolder =  	"/var/www/html/img/pictures/";
	private String profileimagebaseuploadfolder =  	"/var/www/html/img/profiles/";
	
	private String categoryimagebaseuploadfolder =  "/var/www/html/img/categories/";
	private String magazineimagebaseuploadfolder =  "/var/www/html/img/magazines/";
	
	private String baseurl = "http://squapl.com";
	
	private String[] allowDomain = {"http://localhost:4200","http://www.anytimebadminton.com", "http://anytimebadminton.com", "www.anytimebadminton.com","http://ec2-52-77-210-225.ap-southeast-1.compute.amazonaws.com"}; 
	
	public String[] getAllowedDomains() {
		return this.allowDomain;
	}
	
	
	public String getBaseURL() {
		return this.baseurl;
	}
	
	public String getCampaignImageBaseUploadFolder() {
		return this.campaignimageuploadfolder;
	}
	
	public String getArticleImageBaseUploadFolder() {
		return this.articleimagebaseuploadfolder;
	}

	public String getPictureImageBaseUploadFolder() {
		return this.pictureimagebaseuploadfolder;
	}

	public String getProfileImageBaseUploadFolder() {
		return this.profileimagebaseuploadfolder;
	}
	
	public String getCategoryImageBaseUploadFolder() {
		return this.categoryimagebaseuploadfolder;
	}
	public String getMagazineImageBaseUploadFolder() {
		return this.magazineimagebaseuploadfolder;
	}
}
