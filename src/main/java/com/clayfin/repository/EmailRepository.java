package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.Email;

public interface EmailRepository extends JpaRepository<Email, Integer>{
	
}
