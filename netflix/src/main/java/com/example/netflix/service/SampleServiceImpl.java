package com.example.netflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.netflix.entity.SampleEntity;
import com.example.netflix.repository.SampleRepository;

@Service
public class SampleServiceImpl implements SampleService{

	@Autowired
	SampleRepository sampleRepository;
	
	@Override
	public SampleEntity getSample(long id) {
		return sampleRepository.findById(id);
	}
}
