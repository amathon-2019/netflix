package com.example.netflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.netflix.entity.SampleEntity;

public interface SampleRepository extends CrudRepository<SampleEntity, Long>{

	@Query(value="SELECT * FROM SAMPLE WHERE ID = ?1", nativeQuery=true)
	List<SampleEntity> findAllById(long id);
		
	SampleEntity findById(long id);
}
