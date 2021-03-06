package com.jinqiu.app.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jinqiu.app.io.entity.UserEntity;;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);	//query method:variable name must match db
	UserEntity findByUserId(String userId);
	
}
