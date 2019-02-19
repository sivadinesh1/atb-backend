package com.squapl.sa.jparepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.squapl.sa.domain.Category;
import com.squapl.sa.domain.Organiser;



@Repository
public interface OrganiserRepository extends CrudRepository<Organiser, Long> {

	List<Organiser> findAll();
}
