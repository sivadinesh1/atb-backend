package com.squapl.sa.service.serviceimpl;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.squapl.sa.config.RequestFilter;
import com.squapl.sa.domain.Category;
import com.squapl.sa.domain.Organiser;
import com.squapl.sa.domain.Participant;
import com.squapl.sa.domain.Participantsrepo;
import com.squapl.sa.domain.Region;
import com.squapl.sa.domain.Tournament;
import com.squapl.sa.domain.User;

import com.squapl.sa.jparepository.OrganiserRepository;
import com.squapl.sa.jparepository.ParticipantRepository;
import com.squapl.sa.jparepository.ParticipantsrepoRepository;
import com.squapl.sa.jparepository.TournamentRepository;
import com.squapl.sa.service.RegistrationService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	@PersistenceContext
    EntityManager entityManager;
	
	@Autowired
	private OrganiserRepository organiserDao;
	
	@Autowired
	private ParticipantRepository participantDao;
	
	@Autowired
	private ParticipantsrepoRepository ParticipantsrepoDao;
	
	@Autowired
	private TournamentRepository tournamentDao;
	
	 public List<Category> getAllCategories() {   
 		Query query = entityManager.createNativeQuery("SELECT * FROM atbdb.category", Category.class );
		 return query.getResultList();
	 }
	 
	public List<Category>  getCategoriesforGroup(String groupname) {
		logger.debug("GROUP NAME>>"+ groupname);
		Query query = entityManager.createNativeQuery("select * from atbdb.category where name  = ?", Category.class);
		query.setParameter(1, groupname);
		return query.getResultList();
		
	}
	
	public List<Category> getCategoriesforTournament(String tournamentid) {
		logger.debug("GROUP NAME>>"+ tournamentid);
		Query query = entityManager.createNativeQuery("select * from atbdb.category where tournamentid  = ?", Category.class);
		query.setParameter(1, tournamentid);
		return query.getResultList();
		
	}
	 
	 public List<Region> getAllRegions() {   
		Query query = entityManager.createNativeQuery("SELECT * FROM atbdb.region", Region.class );
		 return query.getResultList();
	 }
	 
	 public List<Organiser> getAllOrganisers() {   
		Query query = entityManager.createNativeQuery("SELECT * FROM atbdb.organiser", Organiser.class );
		 return query.getResultList();
	 }
	
	 public List<Tournament> getAllTournaments() {   
		Query query = entityManager.createNativeQuery("SELECT * FROM atbdb.tournament", Tournament.class );
		 return query.getResultList();
	 }
	 
	 public Tournament getTournamentByID(String id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM atbdb.tournament where id = ?", Tournament.class );
		query.setParameter(1, id);
		
		return (Tournament)query.getSingleResult();
		 
	 }
	 
	   public Organiser saveOrganiser(Organiser organiser) {
		   Organiser savedOrganiser = organiserDao.save(organiser);
		   return savedOrganiser;
	    }
	   
		public int checkOrganiserExists(String organisername) {
			Query query = entityManager.createNativeQuery("select * from atbdb.organiser where name  = ? ");
			query.setParameter(1, organisername);
			return query.getResultList().size();
			
		}
		
		public int checkifparticipantexist(String nricno) {
			Query query = entityManager.createNativeQuery("select * from atbdb.participantsrepo where nricno  = ? ");
			query.setParameter(1, nricno);
			return query.getResultList().size();
		}
		
		
		public Region getallareasbyregion(String regionname) {
			Query query = entityManager.createNativeQuery("select * from atbdb.region where region  = ? ", Region.class);
			query.setParameter(1, regionname);
			return (Region)query.getSingleResult();
		}
		
		public int updateOrganiser(Organiser organiser) throws Exception{
			Query query = entityManager.createNativeQuery("update atbdb.organiser set name = ?, primarycontactname = ? , email = ?, primarycontactno = ? , alternatecontactno = ?, address where id = ? ");
			query.setParameter(1, organiser.getName());
			query.setParameter(2, organiser.getPrimarycontactname());
			query.setParameter(3, organiser.getPrimarycontactno());
			query.setParameter(4, organiser.getAlternatecontactno());
			query.setParameter(5, organiser.getAddress());
			query.setParameter(6, organiser.getId());
			
			return query.executeUpdate();
			
			
		}
		
		public Participant saveParticipant(Participant participant) {
		    	Participant savedParticipant = participantDao.save(participant);
	//	    	participantDao.save(savedParticipant);
		
		    	return savedParticipant;
		}
		
		public Participantsrepo addparticipanttorepo(Participant participant) {
			
			Participantsrepo tempparticipantsrepo = new Participantsrepo();
			tempparticipantsrepo.setNricno(participant.getNricno());
			tempparticipantsrepo.setNricname(participant.getNricname());
			tempparticipantsrepo.setDob(participant.getDob());
			tempparticipantsrepo.setGender(participant.getGender());
			tempparticipantsrepo.setRegion(participant.getRegion());
			tempparticipantsrepo.setArea(participant.getArea());
			tempparticipantsrepo.setEmailid(participant.getEmailid());
			tempparticipantsrepo.setContactno(participant.getContactno());
			
			Participantsrepo savedParticipant = ParticipantsrepoDao.save(tempparticipantsrepo);
			
			return savedParticipant;
	}
		  
		public List<Tournament> getAllActiveTournaments() {
			Query query = entityManager.createNativeQuery("SELECT * FROM atbdb.tournament where isactive = 'Y' ", Tournament.class );
			return query.getResultList();
		}
		
		public List<Participant> getRegisteredStatusbyReferenceCode(String referencecode) {
			logger.debug("REF CODE " + referencecode);
			Query query = entityManager.createNativeQuery("select * from atbdb.participant where refrencecode  = ? ", Participant.class);
			query.setParameter(1, referencecode);
			return query.getResultList();
			
		}
 
		public List<Participant> getallParticipantsbyTournamentID(String tournamentid) {
			logger.debug("REF tournamentidCODE " + tournamentid);
			Query query = entityManager.createNativeQuery("select * from atbdb.participant where tournamentid  = ? ", Participant.class);
			query.setParameter(1, tournamentid);
			return query.getResultList();
		}
}



