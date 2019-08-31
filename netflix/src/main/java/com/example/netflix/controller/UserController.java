package com.example.netflix.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.entity.UserEntity;
import com.example.netflix.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	//로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		Map<String, Object> result = userService.login(userEntity);
		//로그인 실패
		if (result==null) 
			throw new Exception();
		
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return new ObjectMapper().writeValueAsString(result);
	}
	
	//회원가입
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		UserEntity user = userService.regist(userEntity);
		if (user==null)
			throw new Exception();
		
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return new ObjectMapper().writeValueAsString(user);
	}
	

}
