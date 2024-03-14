package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.UserLoginProfile;

public interface UserLoginProfileRepository extends JpaRepository<UserLoginProfile, Long> {

	UserLoginProfile findByEmail(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
	
	
}
