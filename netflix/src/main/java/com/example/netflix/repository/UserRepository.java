package com.example.netflix.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.netflix.entity.UserEntity;

public interface  UserRepository extends CrudRepository<UserEntity, Long>{
	
	UserEntity findById(long id);
	
	boolean existsByEmail(String email);
	
	UserEntity findByEmail(String email);

}
