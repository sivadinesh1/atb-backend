package com.squapl.sa.jparepository;

import java.util.List;

import com.squapl.sa.domain.User;

public interface UserDaoCustom {

	User findUsersByQuery();
	User getUser(String name);
	
	
	int updateProfileImgUrl(String profileimgurl, Long userid);
}
