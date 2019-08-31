package com.example.netflix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="account")
@NoArgsConstructor
@Data
public class NetflixAccountEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false)
	private String email;
			
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private int peopleCount;

}
