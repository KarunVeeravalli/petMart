package com.clayfin.common.util;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ProductQuantityDto {
	
	private Long productId;
	
	private Integer quantity;

}
