package com.example.netflix.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.repository.NetflixAccountRepository;
import com.example.netflix.service.SampleService;

@RestController
public class SampleController {

	@Autowired
	SampleService sampleService;
	
	@Autowired
	NetflixAccountRepository netflixAccountRepository;

	@RequestMapping(value="/sample", method=RequestMethod.POST)
	public String sample(@RequestBody NetflixAccountEntity netflixAccountEntity, HttpServletResponse response) throws Exception{
		NetflixAccountEntity finded = netflixAccountRepository.findById(netflixAccountEntity.getId());
		System.out.println(finded);
		//return new ObjectMapper().writeValueAsString(sampleService.getSample(sampleEntity.getId()));
		return null;
	}


}


