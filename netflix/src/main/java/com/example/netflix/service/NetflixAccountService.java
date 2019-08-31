package com.example.netflix.service;

import com.example.netflix.entity.NetflixAccountEntity;

public interface NetflixAccountService {

	NetflixAccountEntity addAccount(NetflixAccountEntity netflixAccountEntity);
	
	NetflixAccountEntity changePassword(NetflixAccountEntity netflixAccountEntity);

}
