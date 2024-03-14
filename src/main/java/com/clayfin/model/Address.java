package com.clayfin.model;

import com.clayfin.common.util.AbstractClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ADDRESS")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Address extends AbstractClass{
	
	private String state;
	
	private String city;
	
	private String doorNum;
	
	private String district;
	
	private Integer pincode;
	
	@ManyToOne
	@JoinColumn(name="user_profile_id")
	@JsonIgnore
	private UserProfile userProfile;
	
}
