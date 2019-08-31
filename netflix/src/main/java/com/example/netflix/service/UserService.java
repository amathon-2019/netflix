package com.example.netflix.service;

import java.util.Map;

import com.example.netflix.entity.UserEntity;

public interface UserService {
	
	//회원가입
	UserEntity regist(UserEntity userEntity);
	
	//로그인
	Map<String, Object> login(UserEntity userEntity) throws Exception;
}
