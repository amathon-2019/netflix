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
import com.example.netflix.repository.UserRepository;

@Service
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	NetflixAccountRepository netflixAccountRepository;
	
	@Autowired
	NetflixAccountUserRelationshipRepository netflixAccountUserRelationshipRepository;
	
	@Autowired
	NetflixAccountUserRelationshipService netflixAccountUserRelationshipService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NetflixAccountService netflixAccountService;
	
	@Autowired
	EmailSender emailSender;

	//해당 날짜에 해당하는 계정 비밀번호 전체 리셋 : 회원들끼리 바꿔주기
	@Transactional
	public void resetAndRegroup(int menu) throws Exception {
		List<NetflixAccountEntity> accountList;
		//해당 날짜에 해당하는 그룹들 불러오기(이전달)
		if(menu==1) 
			accountList = netflixAccountRepository.findByStartDate(LocalDate.now().minusMonths(1l));
		else //테스트용으로 오늘꺼불러오기
			accountList = netflixAccountRepository.findByStartDate(LocalDate.now());
				
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
				
		//그 회원들 다 섞기
		Collections.shuffle(allUsers);
		
		//그룹들 전체 초기화
		for(NetflixAccountEntity account : accountList)
			netflixAccountUserRelationshipService.resetAccount(account);
		
		//그룹들 앞에서부터 채워주기
		Iterator<NetflixAccountEntity> accountIterator = accountList.iterator();
		Iterator<NetflixAccountUserRelationshipEntity> usersIterator = allUsers.iterator();
		NetflixAccountEntity group;
		UserEntity user = new UserEntity();
		
		while (accountIterator.hasNext()) {
			//그룹 하나 꺼내서
			group = accountIterator.next();
			//4명씩 채워준다
			for (int i=0 ; i<4 ; i++) {
				if (usersIterator.hasNext()) {
					user.setId(usersIterator.next().getUserId());
					if (!userRepository.findById(user.getId()).isPayed()) {
						//결제 취소했으면 얘는 카운트에서 빼줌
						i--;
						continue;
					}
					netflixAccountUserRelationshipService.makeRelationship(group, user);
				} else //더이상 사람 남아있지 않으면
					break;
			}
			if (!usersIterator.hasNext())
				break;
		}
		
		//남은건 안쓰는걸로 초기화
		while (accountIterator.hasNext())
			netflixAccountService.setToUnusedAccount(accountIterator.next());
		
		//TODO 바뀐 유저들에게 이메일 보내주기
		user = new UserEntity();
		for (NetflixAccountUserRelationshipEntity changedUser : allUsers) {

			user.setId(changedUser.getUserId());
			NetflixAccountEntity account = netflixAccountService.getUsersAccount(user);
			String body = "<div style=\"background-color: black; align-content: center;\">\n" + 
					"<div align=\"center\">\n" + 
					"	<img src=\"https://lh3.googleusercontent.com/HBVtGFIOgppmhAFcLCr41znBXJScF8OlgRfTdqiLL5NV6b48EmR9zfleS1uwQdlXr9w\">\n" + 
					"	<h1 style=\"color: white\">이제 조금 남았습니다..!</h1>\n" + 
					"	<h3 style=\"color: white\">아래 계정으로 사이트에 로그인 하시면 됩니다.</h3>\n" + 
					"	<h3 style=\"color: white\">요금은 매월 결제일마다 자동으로 청구됩니다.</h3>\n" + 
					"\n" + 
					"	<table style=\"background-color: white; width: 300px; border: 1px solid black\">\n" + 
					"		<tbody>\n" + 
					"			<tr style=\"border: 1px solid black\">\n" + 
					"				<td style=\"border: 1px solid black\">Email</td>\n" + 
					String.format("				<td style=\"border: 1px solid black\">%s</td>\n",account.getEmail()) + 
					"			</tr>\n" + 
					"			<tr style=\"border: 1px solid black\">\n" + 
					"				<td style=\"border: 1px solid black\">Password</td>\n" + 
					String.format("				<td style=\"border: 1px solid black\">%s</td>\n",account.getPassword()) + 
					"			</tr>\n" + 
					"		</tbody>\n" + 
					"	</table>\n" + 
					"</div>\n" + 
					"</div>";
			emailSender.setSUBJECT("4Flix : Your Account!");
			emailSender.setTEXTBODY(body);
			emailSender.setHTMLBODY(body);
			emailSender.setTO(emailSender.getFROM());
			try {
				emailSender.sendEmail();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
	}

	@Override
	public void test() throws Exception {
		System.out.println("working!");
	}

}
