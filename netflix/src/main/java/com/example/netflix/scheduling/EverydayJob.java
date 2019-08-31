package com.example.netflix.scheduling;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.netflix.service.SchedulingService;

@Component
public class EverydayJob {
	
	@Autowired
	SchedulingService schedulingService;
	
	//매일 0시마다
	@Scheduled(cron = "0 0 0 * * ?")
    public void execute() throws Exception {
    	schedulingService.resetAndRegroup(1);
    	System.out.println("Reset Completed At");
    	System.out.println(LocalDateTime.now());
    	System.out.println("==================");
    }
}
