package com.squapl.sa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="organiser")
public class Organiser {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String name;
	private String primarycontactname;
	private String email;
	private String primarycontactno;
	private String alternatecontactno;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrimarycontactname() {
		return primarycontactname;
	}
	public void setPrimarycontactname(String primarycontactname) {
		this.primarycontactname = primarycontactname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrimarycontactno() {
		return primarycontactno;
	}
	public void setPrimarycontactno(String primarycontactno) {
		this.primarycontactno = primarycontactno;
	}
	public String getAlternatecontactno() {
		return alternatecontactno;
	}
	public void setAlternatecontactno(String alternatecontactno) {
		this.alternatecontactno = alternatecontactno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}



	 