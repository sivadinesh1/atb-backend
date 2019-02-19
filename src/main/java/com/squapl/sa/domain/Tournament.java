package com.squapl.sa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tournament")
public class Tournament {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String name;
	private String description;
	private String shortcode;

	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
}
