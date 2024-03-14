package com.clayfin.model;


import com.clayfin.common.util.AbstractClass;
import com.clayfin.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;


@Entity
@Data
@Table(name = "ROLE", 
uniqueConstraints = { 
  @UniqueConstraint(columnNames = "name")
})
public class Role extends AbstractClass{
	

	  @Enumerated(EnumType.STRING)
	  @Column(length = 20)
	  private ERole name;

	  public Role() {

	  }

	  public Role(Long id, ERole name) {
		this.id = id;
		this.name = name;
	}

	public Role(ERole name) {
		this.name = name;
	}
	  
	 
}
