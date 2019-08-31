package com.example.netflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.netflix.entity.NetflixAccountEntity;
import com.example.netflix.repository.NetflixAccountRepository;

@Service
public class NetflixAccountServiceImpl implements NetflixAccountService {

	@Autowired
	NetflixAccountRepository netflixAccountRepository;
	
	//새로운 계정 추가 실패하면 null 성공하면 entity 반환
	@Override
	public NetflixAccountEntity addAccount(NetflixAccountEntity netflixAccountEntity) {
		boolean available = netflixAccountRepository.existsByEmail(netflixAccountEntity.getEmail()); //이메일이 존재하면 true가 리턴됨
		if (available) {
			//이미 존재하는 이름이므로 실패
			return null;
		}
		NetflixAccountEntity savedAccount = netflixAccountRepository.save(netflixAccountEntity); //저장완료
		
		return savedAccount;
	}

	//비밀번호 변경. 실패하면 null 성공하면 entity 반환
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
		
		//비밀번호 변경
		findedAccount.setPassword(netflixAccountEntity.getPassword());
		netflixAccountRepository.save(findedAccount);
		
		return findedAccount;
	}
	
	
	

}
