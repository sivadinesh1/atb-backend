package com.squapl.sa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="matchfixtures")
public class Matchfixtures {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long tournamentid;
	

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	
	private Long categoryid;
	private Integer matchnumber;
	private String matchstarttime;
	private String courtnumber;
	private String home;
	private String away;
	private String winner;
	private String scores;
	private String status;


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

	public Integer getMatchnumber() {
		return matchnumber;
	}

	public void setMatchnumber(Integer matchnumber) {
		this.matchnumber = matchnumber;
	}



	public String getMatchstarttime() {
		return matchstarttime;
	}

	public void setMatchstarttime(String matchstarttime) {
		this.matchstarttime = matchstarttime;
	}

	public String getCourtnumber() {
		return courtnumber;
	}

	public void setCourtnumber(String courtnumber) {
		this.courtnumber = courtnumber;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}