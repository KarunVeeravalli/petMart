package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clayfin.model.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Integer> {
	
	@Query("SELECT t FROM OtpEntity t WHERE t.email = :email ORDER BY t.time DESC")
	List<OtpEntity> getLastOtp(String email);

}
