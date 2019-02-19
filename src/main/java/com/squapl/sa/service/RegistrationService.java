package com.squapl.sa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.squapl.sa.domain.Category;
import com.squapl.sa.domain.Organiser;
import com.squapl.sa.domain.Participant;
import com.squapl.sa.domain.Participantsrepo;
import com.squapl.sa.domain.Region;
import com.squapl.sa.domain.Tournament;

public interface RegistrationService {
	public List<Category> getAllCategories();
	public List<Region> getAllRegions();
	public List<Organiser> getAllOrganisers();
	public List<Category> getCategoriesforGroup(String groupname);
	public List<Category> getCategoriesforTournament(String torunamentid);
	
	public Region getallareasbyregion(String regionname);
	public List<Tournament> getAllTournaments();
	public Tournament getTournamentByID(String id);
	public Participant saveParticipant(Participant participant);
	public List<Tournament> getAllActiveTournaments();
	public int checkifparticipantexist(String nricno);
	public Participantsrepo addparticipanttorepo(Participant participant);
	
	public List<Participant> getRegisteredStatusbyReferenceCode(String referencecode);
	
	public List<Participant> getallParticipantsbyTournamentID(String tournamentid);
		
	
}
