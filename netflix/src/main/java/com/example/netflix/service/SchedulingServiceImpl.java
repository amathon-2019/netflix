package com.example.netflix.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.NetflixAccountUserRelationshipEntity;
import com.example.netflix.entity.UserEntity;
import com.example.netflix.repository.NetflixAccountRepository;
import com.example.netflix.repository.NetflixAccountUserRelationshipRepository;

@Service
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	NetflixAccountRepository netflixAccountRepository;
	
	@Autowired
	NetflixAccountUserRelationshipRepository netflixAccountUserRelationshipRepository;
	
	@Autowired
	NetflixAccountUserRelationshipService netflixAccountUserRelationshipService;
	
	@Autowired
	NetflixAccountService netflixAccountService;
	
	//해당 날짜에 해당하는 계정 비밀번호 전체 리셋 : 회원들끼리 바꿔주기
	@Transactional
	public void resetAndRegroup() {
		System.out.println("@");
		//해당 날짜에 해당하는 그룹들 불러오기(이전달)
		//List<NetflixAccountEntity> accountList = netflixAccountRepository.findByStartDate(LocalDate.now().minusMonths(1l));
		
		//테스트용 
		List<NetflixAccountEntity> accountList = netflixAccountRepository.findByStartDate(LocalDate.now());
				
		//그 그룹들에 해당하는 회원들 다 불러오기
		List<NetflixAccountUserRelationshipEntity> allUsers = new LinkedList<NetflixAccountUserRelationshipEntity>();
		List<NetflixAccountUserRelationshipEntity> tempUsers;
		
		
		for(NetflixAccountEntity account : accountList) {
			//비밀번호 변경 && 오늘날짜로 바꿔주기
			netflixAccountService.changePassword(account);
			//사람들 모아서 추가
			tempUsers = netflixAccountUserRelationshipRepository.findByAccountId(account.getId());
			allUsers.addAll(tempUsers);
		}
		
		System.out.println("회원들 전체다");
		System.out.println(allUsers);
		
		//그 회원들 다 섞기
		Collections.shuffle(allUsers);
		
		//그룹들 전체 초기화
		for(NetflixAccountEntity account : accountList)
			netflixAccountUserRelationshipService.resetAccount(account);
		
		//그룹들 앞에서부터 채워주기
		Iterator<NetflixAccountEntity> accountIterator = accountList.iterator();
		Iterator<NetflixAccountUserRelationshipEntity> usersIterator = allUsers.iterator();
		NetflixAccountEntity group;
		UserEntity user;
		
		while (accountIterator.hasNext()) {
			group = accountIterator.next();
			for (int i=0 ; i<4 ; i++) {
				if (usersIterator.hasNext()) {
					user = new UserEntity();
					user.setId(usersIterator.next().getUserId());
					netflixAccountUserRelationshipService.makeRelationship(group, user);
				} else 
					break;
			}
			if (!usersIterator.hasNext())
				break;
		}
		
		//남은건 안쓰는걸로 초기화
		while (accountIterator.hasNext())
			netflixAccountService.setToUnusedAccount(accountIterator.next());
		
		//TODO 바뀐 유저들에게 이메일 보내주기
	}

	@Override
	public void test() {
		System.out.println("working!");
	}

}
