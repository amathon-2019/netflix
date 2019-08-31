package com.example.netflix.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.netflix.entity.NetflixAccountEntity;


public interface NetflixAccountRepository extends CrudRepository<NetflixAccountEntity, Long>{
	
	NetflixAccountEntity findById(long id);
	
	boolean existsByEmail(String email);
	
	NetflixAccountEntity findByEmail(String email);

}
