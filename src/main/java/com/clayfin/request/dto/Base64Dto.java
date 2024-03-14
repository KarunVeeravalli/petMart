package com.clayfin.request.dto;

import jakarta.validation.constraints.NotBlank;

public class Base64Dto {
	@NotBlank
	private String base64EncodedString;
	
	private Long numOfItems;

	public String getBase64EncodedString() {
		return base64EncodedString;
	}

	public void setBase64EncodedString(String base64EncodedString) {
		this.base64EncodedString = base64EncodedString;
	}

	public Long getNumOfItems() {
		return numOfItems;
	}

	public void setNumOfItems(Long numOfItems) {
		this.numOfItems = numOfItems;
	}

}
