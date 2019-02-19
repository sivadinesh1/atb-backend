package com.squapl.sa.jparepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.squapl.sa.domain.Participant;
import com.squapl.sa.domain.Participantsrepo;


@Repository
public interface ParticipantsrepoRepository extends CrudRepository<Participantsrepo, Long> {

	
}