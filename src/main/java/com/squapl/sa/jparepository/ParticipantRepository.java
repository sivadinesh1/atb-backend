package com.squapl.sa.jparepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.squapl.sa.domain.Participant;


@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

	List<Participant> findAll();
}