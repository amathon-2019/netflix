package com.example.netflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.entity.NetflixAccountUserRelationshipEntity;
import com.example.netflix.entity.UserEntity;
import com.example.netflix.repository.NetflixAccountRepository;
import com.example.netflix.repository.NetflixAccountUserRelationshipRepository;

@Service
public class NetflixAccountUserRelationshipServiceImpl implements NetflixAccountUserRelationshipService{

	@Autowired
	NetflixAccountUserRelationshipRepository netflixAccountUserRelationshipRepository;
	
	@Autowired
	NetflixAccountRepository netflixAccountRepository;
	
	// 넷플릭스 계정 하나에 회원 한명 할당. 
	@Override
	public void makeRelationship(NetflixAccountEntity netflixAccountEntity, UserEntity userEntity) throws Exception {
		
		// 두개의 아이디를 이용해 추가
		NetflixAccountUserRelationshipEntity netflixAccountUserRelationshipEntity = new NetflixAccountUserRelationshipEntity();
		netflixAccountUserRelationshipEntity.setAccountId(netflixAccountEntity.getId());
		netflixAccountUserRelationshipEntity.setUserId(userEntity.getId());
		netflixAccountUserRelationshipRepository.save(netflixAccountUserRelationshipEntity);
		
		// 사람 수를 한명 늘려 줌
		NetflixAccountEntity findedAccount = netflixAccountRepository.findById(netflixAccountEntity.getId());
		findedAccount.setPeopleCount(findedAccount.getPeopleCount()+1);
		netflixAccountRepository.save(findedAccount);
		
	}

	// 넷플릭스 계정에 할당된 회원 삭제
	@Override
	public void deleteRelationship(NetflixAccountEntity netflixAccountEntity, UserEntity userEntity) throws Exception {
		
		// 두개의 아이디를 이용해 삭제
		netflixAccountUserRelationshipRepository.deleteByAccountIdAndUserId(netflixAccountEntity.getId(), userEntity.getId());
		
		// 사람 수를 한명 줄여
		NetflixAccountEntity findedAccount = netflixAccountRepository.findById(netflixAccountEntity.getId());
		findedAccount.setPeopleCount(findedAccount.getPeopleCount()-1);
		netflixAccountRepository.save(findedAccount);

	}

	//해당 계정에 할당된 모든 relstionship 삭제
	@Override
	public void resetAccount(NetflixAccountEntity netflixAccountEntity) throws Exception {
		//relationship 전체 삭제
		netflixAccountUserRelationshipRepository.deleteByAccountId(netflixAccountEntity.getId());
		//할당된 회원 0명으로 저장
		netflixAccountEntity.setPeopleCount(0);
		netflixAccountRepository.save(netflixAccountEntity);
		
	}

	
}
