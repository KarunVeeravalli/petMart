package com.clayfin.model;

import java.util.HashSet;
import java.util.Set;

import com.clayfin.common.util.AbstractClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USER_LOGIN_PROFILE", 
uniqueConstraints = { 
  @UniqueConstraint(columnNames = "USERNAME"),
  @UniqueConstraint(columnNames = "EMAIL") 
})
public class UserLoginProfile extends AbstractClass{
	
		
	
	  @NotBlank
	  @Size(max = 20)
	  @Column(name = "USERNAME")
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  @Column(name = "EMAIL")
	  private String email;

	  @NotBlank
	  @Size(max = 120)
	  @Column(name = "ENCODED_PASSWORD")
	  private String password;

	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "USER_LOGIN_PROFILE_ROLES", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles = new HashSet<>();
	  
	public UserLoginProfile() {
		
	}
	
	public UserLoginProfile( String username, String email,String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	 
	
}
