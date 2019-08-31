package com.example.netflix.service;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.UserEntity;

public interface NetflixAccountUserRelationshipService {
	
	void makeRelationship(NetflixAccountEntity netflixAccountEntity, UserEntity userEntity);
	
	void deleteRelationship(NetflixAccountEntity netflixAccountEntity, UserEntity userEntity);

	void resetAccount(NetflixAccountEntity netflixAccountEntity);
}
