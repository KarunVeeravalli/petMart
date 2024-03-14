package com.clayfin.request.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import lombok.Data;

@Data
@CsvRecord(separator = ",")
public class ProductBase64DTO {
	
	@DataField(pos = 1)
	private String name;
	
	@DataField(pos = 2)
	private String discription;
	
	@DataField(pos = 3)
	private Double price;
	
	@DataField(pos = 4)
	private Boolean productAvailability;

}
