package com.example.netflix.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.netflix.entity.NetflixAccountUserRelationshipEntity;

public interface NetflixAccountUserRelationshipRepository extends CrudRepository<NetflixAccountUserRelationshipEntity, Long> {
		
	NetflixAccountUserRelationshipEntity findByAccountIdAndUserId(long accountId, long userId);

	NetflixAccountUserRelationshipEntity findByUserId(long userId);
	
	long deleteByAccountIdAndUserId(long accountId, long userId);

	List<NetflixAccountUserRelationshipEntity> findByAccountId(long accountId);
	
	void deleteByAccountId(long accountId);
}
