package com.example.netflix.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.UserEntity;
import com.example.netflix.service.NetflixAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class NetflixAccountController {

	@Autowired
	NetflixAccountService netflixAccountService;

	//계정 추가 (이메일만 보내주면 됨) 
	@RequestMapping(value="/account/add", method=RequestMethod.POST)
	public String addAccount(@RequestBody NetflixAccountEntity netflixAccountEntity, HttpServletResponse response) throws Exception{
		try {
			NetflixAccountEntity newAccount = netflixAccountService.addAccount(netflixAccountEntity);
			//실패
			if (newAccount==null)
				throw new Exception();

			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ObjectMapper().writeValueAsString(newAccount);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	//비밀번호 변경 (랜덤으로, 이메일만 보내주면 됨)
	@RequestMapping(value="/account/change", method=RequestMethod.POST)
	public String changeAccountPassword(@RequestBody NetflixAccountEntity netflixAccountEntity, HttpServletResponse response) throws Exception{
		try {
			NetflixAccountEntity changedAccount = netflixAccountService.changePassword(netflixAccountEntity);

			//실패
			if (changedAccount==null)
				throw new Exception();

			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ObjectMapper().writeValueAsString(changedAccount);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}	

	// 유저에게 할당 된 계정 불러오기 (id만 불러오면 됨)
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String getAccount(@RequestBody UserEntity userEntity, HttpServletResponse response) throws Exception{
		try {
			NetflixAccountEntity account = netflixAccountService.getUsersAccount(userEntity);

			//실패
			if (account==null)
				throw new Exception();

			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ObjectMapper().writeValueAsString(account);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}	

}
