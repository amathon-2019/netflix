package com.example.netflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.netflix.service.NetflixAccountUserRelationshipService;

@RestController
public class NetflixAccountUserRelationshipController {

	@Autowired
	NetflixAccountUserRelationshipService netflixAccountUserRelationshipService;
	

}
