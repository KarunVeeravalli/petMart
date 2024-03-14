package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.UnauthUser;

public interface UnauthUserRepo extends JpaRepository<UnauthUser, Long>{

	UnauthUser findByEmail(String mail);

}
