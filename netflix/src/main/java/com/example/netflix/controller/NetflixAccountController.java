package com.example.netflix.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.service.NetflixAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class NetflixAccountController {

	@Autowired
	NetflixAccountService netflixAccountService;
	
	//계정 추가
	@RequestMapping(value="/account/add", method=RequestMethod.POST)
	public String addAccount(@RequestBody NetflixAccountEntity netflixAccountEntity, HttpServletResponse response) throws Exception{
		NetflixAccountEntity newAccount = netflixAccountService.addAccount(netflixAccountEntity);
		
		//실패
		if (newAccount==null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return new ObjectMapper().writeValueAsString(newAccount);
	}
	
	//비밀번호 변경
	@RequestMapping(value="/account/change", method=RequestMethod.POST)
	public String changeAccountPassword(@RequestBody NetflixAccountEntity netflixAccountEntity, HttpServletResponse response) throws Exception{
		NetflixAccountEntity changedAccount = netflixAccountService.changePassword(netflixAccountEntity);
		
		//실패
		if (changedAccount==null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return new ObjectMapper().writeValueAsString(changedAccount);
	}	

}
