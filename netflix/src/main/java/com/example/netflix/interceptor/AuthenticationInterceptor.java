package com.example.netflix.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.netflix.service.JwtService;


public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired 
	private JwtService jwtService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
	    	String token = request.getHeader("token");
			if (!jwtService.checkJwt(token)) { 
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			return true;
		} catch (Exception e) { 
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
    }
}
