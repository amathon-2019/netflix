package com.example.netflix.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordFactory {

	private char getCharacter() {
		return (char)('a'+(int)(Math.random()*27));
	}
	
	private int getInteger() {
		return (int)(Math.random()*10);
	}
	
	public String getPassword() {
		String password = "";
		for (int i=0 ; i<10 ; i++) {
			if ((int)(Math.random()*2) == 1) {
				password += Character.toString(getCharacter());
			} else
				password += Integer.toString(getInteger());
		}
		return password;
	}
	
}
