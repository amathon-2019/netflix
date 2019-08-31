package com.example.netflix.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.netflix.entity.UserEntity;
import com.example.netflix.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtService jwtService;


	// 이메일, 비밀번호 받아서 암호화 후 저장. 이미 존재하는 이메일이거나 실패하면 return null, 아니면 Entity 리턴
	@Override
	public UserEntity regist(UserEntity userEntity) {
		boolean available = userRepository.existsByEmail(userEntity.getEmail()); //이메일이 존재하면 true가 리턴됨
		if (available) {
			//이미 존재하는 이름이므로 실패
			return null;
		}
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword())); //패스워드 암호화
		UserEntity savedUser = userRepository.save(userEntity); //저장완료
		savedUser.setPassword(""); //비밀번호 숨기기
		
		return savedUser;
	}

	// 비밀번호 맞는지 확인 후 맞으면 토큰 map 리턴, 틀리면 null 리턴
	@Override
	public Map<String, Object> login(UserEntity userEntity) throws Exception {
		boolean available = userRepository.existsByEmail(userEntity.getEmail()); //이메일 있는지 확인
		if (!available) return null ; //없으면 null 리턴
		
		UserEntity findedUser = userRepository.findByEmail(userEntity.getEmail()); //암호화된 비밀번호 받아와
		if (passwordEncoder.matches(userEntity.getPassword(), findedUser.getPassword())) {//비밀번호 체크 시 맞으면 
			Map<String, Object> result = new HashMap<>();
			result.put("token", jwtService.makeJwt(findedUser));
			return result; // "token" : 토큰 형식의 map
		}
		else
			return null; //비밀번호가 다르면 null 리턴
	}
	

}
