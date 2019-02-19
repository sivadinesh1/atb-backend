package com.squapl.sa.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	private Long tournamentid;
	private String name;
	private Integer noofplayers;
	private Integer agerestriction;
	private String ethnicity;
	private Integer registrationfee;
	private String comments;
	
	@OneToMany
	@JoinColumn(name = "categoryid", referencedColumnName="id", insertable=false, updatable = false)
	private List <Matchfixtures> matchfixtures;
  
	public List<Matchfixtures> getMatchfixtures() {
		return matchfixtures;
	}
	public void setMatchfixtures(List<Matchfixtures> matchfixtures) {
		this.matchfixtures = matchfixtures;
	}
	public Long getTournamentid() {
		return tournamentid;
	}
	public void setTournamentid(Long tournamentid) {
		this.tournamentid = tournamentid;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
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
	
	public String getSexrestriction() {
		return sexrestriction;
	}
	public void setSexrestriction(String sexrestriction) {
		this.sexrestriction = sexrestriction;
	}

	public Integer getNoofplayers() {
		return noofplayers;
	}
	public void setNoofplayers(Integer noofplayers) {
		this.noofplayers = noofplayers;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	
	private String sexrestriction;
	  public void setAgerestriction(int agerestriction) {
		this.agerestriction = agerestriction;
	}
	public Integer getAgerestriction() {
		return agerestriction;
	}
	public void setAgerestriction(Integer agerestriction) {
		this.agerestriction = agerestriction;
	}



	public Integer getRegistrationfee() {
		return registrationfee;
	}
	public void setRegistrationfee(Integer registrationfee) {
		this.registrationfee = registrationfee;
	}
	public void setRegistrationfee(int registrationfee) {
		this.registrationfee = registrationfee;
	}

}
