package com.example.netflix.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.netflix.entity.NetflixAccountUserRelationshipEntity;

public interface NetflixAccountUserRelationshipRepository extends CrudRepository<NetflixAccountUserRelationshipEntity, Long> {
		
	NetflixAccountUserRelationshipEntity findByAccountIdAndUserId(long accountId, long userId);

	long deleteByAccountIdAndUserId(long accountId, long userId);

}
