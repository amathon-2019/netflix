package com.example.netflix.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.NetflixAccountUserRelationshipEntity;
import com.example.netflix.entity.UserEntity;
import com.example.netflix.repository.NetflixAccountRepository;
import com.example.netflix.repository.NetflixAccountUserRelationshipRepository;

@Service
public class NetflixAccountServiceImpl implements NetflixAccountService {

	@Autowired
	NetflixAccountRepository netflixAccountRepository;
	
	@Autowired
	NetflixAccountUserRelationshipRepository netflixAccountRelationshipRepository;
	
	@Autowired
	PasswordFactory passwordFactory;
	
	//새로운 계정 추가 실패하면 null 성공하면 entity 반환
	@Override
	public NetflixAccountEntity addAccount(NetflixAccountEntity netflixAccountEntity) {
		boolean available = netflixAccountRepository.existsByEmail(netflixAccountEntity.getEmail()); //이메일이 존재하면 true가 리턴됨
		if (available) {
			//이미 존재하는 이름이므로 실패
			return null;
		}
		netflixAccountEntity.setStartDate(LocalDate.now());
		System.out.println("!@#!@#");
		System.out.println(netflixAccountEntity.getStartDate());
		netflixAccountEntity.setPassword(passwordFactory.getPassword());
		NetflixAccountEntity savedAccount = netflixAccountRepository.save(netflixAccountEntity); //저장완료
		
		return savedAccount;
	}

	//비밀번호 랜덤으로 변경. 실패하면 null 성공하면 entity 반환
	@Override
	public NetflixAccountEntity changePassword(NetflixAccountEntity netflixAccountEntity) {
		NetflixAccountEntity findedAccount;
		if (netflixAccountEntity.getId()!=0) {
			//id로 식별 가능 하면
			findedAccount = netflixAccountRepository.findById(netflixAccountEntity.getId());
		} else {
			//이메일로 식별
			findedAccount = netflixAccountRepository.findByEmail(netflixAccountEntity.getEmail());
		}
		if (findedAccount == null)
			return null;
		
		//비밀번호 변경 (랜덤으로)
		findedAccount.setPassword(passwordFactory.getPassword());
		netflixAccountRepository.save(findedAccount);
		
		return findedAccount;
	}

	
	//해당 회원에게 할당 된 계정 불러오기
	@Override
	public NetflixAccountEntity getUsersAccount(UserEntity userEntity) {
		NetflixAccountUserRelationshipEntity relationship = netflixAccountRelationshipRepository.findByUserId(userEntity.getId());
		NetflixAccountEntity account = netflixAccountRepository.findById(relationship.getAccountId());
		return account;
	}

	//해당 계정은 아무도 안쓰는 계정이므로 안쓴거로 설정해주기(날짜 이상한걸로 바꿔줌 : 10년전껄로)
	@Override
	public void setToUnusedAccount(NetflixAccountEntity netflixAccountEntity) {
		netflixAccountEntity.setStartDate(LocalDate.now().minusYears(10));
		netflixAccountEntity.setPassword(passwordFactory.getPassword());
		netflixAccountRepository.save(netflixAccountEntity);
	}

}
