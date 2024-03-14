package com.clayfin.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.clayfin.common.util.AbstractClass;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "USER_PROFILE" ,
 uniqueConstraints = @UniqueConstraint(columnNames =  {"EMAIL","USERNAME"}))
@Data
public class UserProfile extends AbstractClass {
	
	  @NotBlank
	  @Size(max = 20)
	  @Column(name = "USERNAME")
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  @Column(name = "EMAIL")
	  private String email;
	  
	  @OneToMany(mappedBy = "userProfile")
	  @JsonIgnore
	  private List<Address> address;
	  
	  
	  @OneToMany(mappedBy = "userProfile")
	  @JsonIgnore
	  private List<Orders> orders;
	  

	  @OneToMany(mappedBy = "userProfile")
	  @JsonIgnore
	  private List<Transaction> transactions;
	  
	  
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "USER_PROFILE_ROLES", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles = new HashSet<>();
	  
	  @Lob
	  private String profileImg;
}
