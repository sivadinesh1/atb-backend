package com.squapl.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="participant")
public class Participant {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private Long tournamentid;
	private String nricname;
	private String nricno;
	private String dob;
	private String gender;
	private String region;
	private String area;
	
	private String category;
	private String emailid;
	private String contactno;
	private String regfirstname;
	private String reglastname;
	private String designation;
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	private Date regdatetime;
	private String reguseremail;
	private String regcontactno;
	
	
	private String companyname;
	private String teamname;
	
	
	public String getRegcontactno() {
		return regcontactno;
	}
	public void setRegcontactno(String regcontactno) {
		this.regcontactno = regcontactno;
	}
	private String refrencecode;
	private String tshirtsize;
	
	
	
	
	public Long getTournamentid() {
		return tournamentid;
	}
	public void setTournamentid(Long tournamentid) {
		this.tournamentid = tournamentid;
	}
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
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getRegfirstname() {
		return regfirstname;
	}
	public void setRegfirstname(String regfirstname) {
		this.regfirstname = regfirstname;
	}
	public String getReglastname() {
		return reglastname;
	}
	public void setReglastname(String reglastname) {
		this.reglastname = reglastname;
	}
	public Date getRegdatetime() {
		return regdatetime;
	}
	public void setRegdatetime(Date regdatetime) {
		this.regdatetime = regdatetime;
	}
	public String getReguseremail() {
		return reguseremail;
	}
	public void setReguseremail(String reguseremail) {
		this.reguseremail = reguseremail;
	}

	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getRefrencecode() {
		return refrencecode;
	}
	public void setRefrencecode(String refrencecode) {
		this.refrencecode = refrencecode;
	}


	public String getTshirtsize() {
		return tshirtsize;
	}
	public void setTshirtsize(String tshirtsize) {
		this.tshirtsize = tshirtsize;
	}

	
	
	

}
