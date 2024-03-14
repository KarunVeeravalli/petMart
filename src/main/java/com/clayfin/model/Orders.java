package com.clayfin.model;

import java.util.List;
import java.util.Map;

import com.clayfin.common.util.AbstractClass;
import com.clayfin.common.util.ProductQuantityDto;
import com.clayfin.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ORDERS")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Orders extends AbstractClass{
	

	private double totalPrice;
	
	@OneToOne
	private Address address;
	
	private OrderType orderType;
	
	private Integer totalItems;
	
	@ManyToOne
	@JoinColumn(name="user_profile_id")
	private UserProfile userProfile;
	
	private String orderStatus;
	
	@ElementCollection
	private List<ProductQuantityDto> products;
	
	@OneToOne
	@JsonIgnore
	private Transaction transaction;
	
}
