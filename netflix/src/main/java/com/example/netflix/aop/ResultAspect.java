package com.example.netflix.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ResultAspect {
/*
	@AfterReturning("execution(* com..*(..))")
    public void success(JoinPoint joinPoint) throws Throwable {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }
	
	@AfterThrowing("execution(* com..*(..))")
    public void fail(JoinPoint joinPoint) throws Throwable {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
*/
}
