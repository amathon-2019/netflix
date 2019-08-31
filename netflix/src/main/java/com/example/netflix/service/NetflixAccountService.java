package com.example.netflix.service;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.UserEntity;

public interface NetflixAccountService {

	NetflixAccountEntity addAccount(NetflixAccountEntity netflixAccountEntity) throws Exception;
	
	NetflixAccountEntity changePassword(NetflixAccountEntity netflixAccountEntity) throws Exception;
	
	NetflixAccountEntity getUsersAccount(UserEntity userEntity) throws Exception;
	
	void setToUnusedAccount(NetflixAccountEntity netflixAccountEntity) throws Exception;

}
