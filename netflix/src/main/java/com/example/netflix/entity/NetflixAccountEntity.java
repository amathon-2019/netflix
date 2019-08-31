package com.example.netflix.entity;

import java.time.LocalDate;

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
	
	@Column
	private String email;
			
	@Column
	private String password;
	
	@Column
	private int peopleCount;

	@Column
	private LocalDate startDate;
}
