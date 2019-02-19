package com.squapl.sa.jparepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.squapl.sa.domain.User;
@Repository
public interface UserDao extends CrudRepository<User, Long> {
	User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    
    void deleteByUserId(Long userId);
	User findByUserId(Long userId);
	List<User> findByEnabled(boolean b);
	
	

}
