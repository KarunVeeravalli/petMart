package com.clayfin.request.dto;

import java.util.List;

import com.clayfin.common.util.ProductQuantityDto;
import com.clayfin.enums.OrderType;

import lombok.Data;

@Data
public class OrderRequestDto {
	
	List<ProductQuantityDto> productQuantityDtos;
	
	Long AddressId;
	
	OrderType orderType;
	
	
}
