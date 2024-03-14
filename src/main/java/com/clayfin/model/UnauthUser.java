package com.clayfin.model;

import com.clayfin.common.util.AbstractClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UnauthUser extends AbstractClass{
	
	private String email;
	
	private String username;
	
	private String password;
}
