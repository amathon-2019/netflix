package com.example.netflix.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.entity.SampleEntity;
import com.example.netflix.service.SampleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SampleController {

	@Autowired
	SampleService sampleService;

	@RequestMapping(value="/sample", method=RequestMethod.POST)
	public String insertArticle(@RequestBody SampleEntity sampleEntity, HttpServletResponse response) throws Exception{
		return new ObjectMapper().writeValueAsString(sampleService.getSample(sampleEntity.getId()));
	}


}


