package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.enums.ERole;
import com.clayfin.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(ERole roleUser);

}
