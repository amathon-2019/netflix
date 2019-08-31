package com.example.netflix.service;

import java.util.Map;

import com.example.netflix.entity.UserEntity;

public interface UserService {
	
	//회원가입
	UserEntity regist(UserEntity userEntity) throws Exception;
	
	//로그인
	Map<String, Object> login(UserEntity userEntity) throws Exception;
	
	//결제 
	void pay(UserEntity userEntity) throws Exception;
	
	//비밀번호변경
	UserEntity changePassword(UserEntity userEntity) throws Exception;
	
	//결제취소
	void cancelPay(UserEntity userEntity) throws Exception;
}
