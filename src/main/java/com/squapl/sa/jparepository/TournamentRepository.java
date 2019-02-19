package com.squapl.sa.jparepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.squapl.sa.domain.Tournament;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {

	List<Tournament> findAll();
}