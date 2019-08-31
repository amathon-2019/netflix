package com.example.netflix.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.UserEntity;
import com.example.netflix.service.NetflixAccountService;
import com.example.netflix.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	NetflixAccountService netflixAccountService;

	//로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		try {
			Map<String, Object> result = userService.login(userEntity);
			//로그인 실패
			if (result==null) 
				throw new Exception();

			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	//회원가입
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		try {
			UserEntity user = userService.regist(userEntity);
			if (user==null)
				throw new Exception();

			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ObjectMapper().writeValueAsString(user);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;

		}
	}

	//결제
	@RequestMapping(value="/pay", method=RequestMethod.POST)
	public String pay(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		try {
			userService.pay(userEntity);
			NetflixAccountEntity account = netflixAccountService.getUsersAccount(userEntity);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ObjectMapper().writeValueAsString(account);
		} catch(Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	//비밀번호 변경
	@RequestMapping(value="/user/password", method=RequestMethod.POST)
	public String changePassword(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		try {
			UserEntity changedUser = userService.changePassword(userEntity);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ObjectMapper().writeValueAsString(changedUser);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
	
	//결제 취소
	@RequestMapping(value="/pay/cancel", method=RequestMethod.POST)
	public String canclePay(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		try {
			userService.cancelPay(userEntity);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return null;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
}
