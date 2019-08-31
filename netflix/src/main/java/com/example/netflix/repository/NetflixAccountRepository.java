package com.example.netflix.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.netflix.entity.NetflixAccountEntity;


public interface NetflixAccountRepository extends CrudRepository<NetflixAccountEntity, Long>{
	
	NetflixAccountEntity findById(long id);
	
	boolean existsByEmail(String email);
	
	NetflixAccountEntity findByEmail(String email);
	
	List<NetflixAccountEntity> findByPeopleCountAndStartDate(int peopleCount, LocalDate startDate);
	
	List<NetflixAccountEntity> findByStartDate(LocalDate startDate);

}
