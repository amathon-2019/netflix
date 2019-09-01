package com.example.netflix.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.repository.NetflixAccountRepository;
import com.example.netflix.service.SampleService;
import com.example.netflix.service.SchedulingService;

@RestController
public class SampleController {

	@Autowired
	SampleService sampleService;
	
	@Autowired
	NetflixAccountRepository netflixAccountRepository;
	
	@Autowired
	SchedulingService schedulingService;

	@RequestMapping(value="/sample", method=RequestMethod.GET)
	public String sample(HttpServletResponse response) throws Exception{
		schedulingService.resetAndRegroup(0);
		
		return null;
	}


}


