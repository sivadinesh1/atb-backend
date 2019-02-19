package com.squapl.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="participantsrepo")
public class Participantsrepo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String nricname;
	private String nricno;
	private String dob;
	private String gender;
	private String region;
	private String area;
	private String emailid;
	private String contactno;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNricname() {
		return nricname;
	}
	public void setNricname(String nricname) {
		this.nricname = nricname;
	}
	public String getNricno() {
		return nricno;
	}
	public void setNricno(String nricno) {
		this.nricno = nricno;
	}

	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	



	
	
	

}
