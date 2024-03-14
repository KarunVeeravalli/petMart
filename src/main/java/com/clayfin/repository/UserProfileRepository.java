package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

	UserProfile findByUsername(String usernameFromToken);
	
	UserProfile findByEmail(String email);

}
