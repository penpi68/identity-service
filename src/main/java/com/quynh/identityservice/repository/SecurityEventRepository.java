package com.quynh.identityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quynh.identityservice.entity.SecurityEvent;

public interface SecurityEventRepository extends JpaRepository<SecurityEvent, Long> {

	
	List<SecurityEvent> findAllByOrderByIdAsc();
}
