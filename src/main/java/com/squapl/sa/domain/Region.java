package com.squapl.sa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="region")
public class Region {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String region;
	private String areas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String area) {
		this.areas = areas;
	}
	

}



	 