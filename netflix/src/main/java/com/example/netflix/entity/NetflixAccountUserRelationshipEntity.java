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
@Table(name="accountuserrelationship")
@NoArgsConstructor
@Data
public class NetflixAccountUserRelationshipEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false)
	private long accountId;
			
	@Column(nullable=false)
	private long userId;

}
