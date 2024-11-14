package com.quynh.identityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quynh.identityservice.entity.SecurityEvent;


@Repository
public interface SecurityEventRepository extends JpaRepository<SecurityEvent, Long> {

	
	List<SecurityEvent> findAllByOrderByIdAsc();
}
