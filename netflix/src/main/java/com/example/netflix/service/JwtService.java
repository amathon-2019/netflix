package com.example.netflix.service;

import com.example.netflix.entity.UserEntity;

public interface JwtService {
	
	String makeJwt(UserEntity userEntity) throws Exception;
	
	boolean checkJwt(String jwt) throws Exception;

}
