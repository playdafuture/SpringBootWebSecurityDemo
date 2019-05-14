package com.jinqiu.app.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jinqiu.app.io.entity.UserEntity;;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
}
