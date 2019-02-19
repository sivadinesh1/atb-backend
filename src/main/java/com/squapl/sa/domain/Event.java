package com.squapl.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="event")
public class Event {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private Long tournamentid;
	private String description;
	private Long organiserid;
	private String venue;
	//private String eventstdate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "en-IN", timezone = "Asia/Kolkata")
    private Date eventstdate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "en-IN", timezone = "Asia/Kolkata")
    private Date eventenddate;
	
	//private String eventenddate;
	private String starttime;
	private String endtime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "en-IN", timezone = "Asia/Kolkata")
    private Date lastdatereg;
	
	//private String lastdatereg;
	private String contactno1;
	private String contactno2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTournamentid() {
		return tournamentid;
	}
	public void setTournamentid(Long tournamentid) {
		this.tournamentid = tournamentid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getOrganiserid() {
		return organiserid;
	}
	public void setOrganiserid(Long organiserid) {
		this.organiserid = organiserid;
	}
	public String getVenue() {
		return venue;
	}
	public Date getEventstdate() {
		return eventstdate;
	}
	public void setEventstdate(Date eventstdate) {
		this.eventstdate = eventstdate;
	}
	public Date getEventenddate() {
		return eventenddate;
	}
	public void setEventenddate(Date eventenddate) {
		this.eventenddate = eventenddate;
	}
	public Date getLastdatereg() {
		return lastdatereg;
	}
	public void setLastdatereg(Date lastdatereg) {
		this.lastdatereg = lastdatereg;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public String getContactno1() {
		return contactno1;
	}
	public void setContactno1(String contactno1) {
		this.contactno1 = contactno1;
	}
	public String getContactno2() {
		return contactno2;
	}
	public void setContactno2(String contactno2) {
		this.contactno2 = contactno2;
	}
}