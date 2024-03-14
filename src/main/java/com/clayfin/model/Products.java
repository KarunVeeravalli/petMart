package com.clayfin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.clayfin.common.util.AbstractClass;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCTS")
@Data
public class Products extends AbstractClass implements Serializable { 
	
	private static final long serialVersionUID = -7678021941462795252L;
	
	@Column(name = "PRODUCT_NAME")
	private String name;
	
	private String discription;
	
	private Double price;
	
	private Boolean productAvailability;
	
}
