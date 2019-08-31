package com.example.netflix.service;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.UserEntity;

public interface NetflixAccountService {

	NetflixAccountEntity addAccount(NetflixAccountEntity netflixAccountEntity);
	
	NetflixAccountEntity changePassword(NetflixAccountEntity netflixAccountEntity);
	
	NetflixAccountEntity getUsersAccount(UserEntity userEntity);

}
