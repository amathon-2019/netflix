package com.example.netflix.service;

public interface SchedulingService {

	//menu 0은 테스트(오늘꺼 섞기) 1은 실제 한달 전꺼 섞기
	void resetAndRegroup(int menu) throws Exception;
	
	void test() throws Exception;
}
