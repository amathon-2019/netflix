package com.example.netflix.service;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.UserEntity;

public interface NetflixAccountUserRelationshipService {
	
	void makeRelationship(NetflixAccountEntity netflixAccountEntity, UserEntity userEntity) throws Exception;
	
	void deleteRelationship(NetflixAccountEntity netflixAccountEntity, UserEntity userEntity) throws Exception;

	void resetAccount(NetflixAccountEntity netflixAccountEntity) throws Exception;
}
