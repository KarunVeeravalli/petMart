package com.clayfin.model;

import com.clayfin.common.util.AbstractClass;
import com.clayfin.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Transaction extends AbstractClass{
	
	private Double amount;
	
	@ManyToOne
	@JsonIgnore
	private UserProfile  userProfile;
	
	private String Location;
	
	private Status status;
	
	@OneToOne()
	private Orders order;
}
