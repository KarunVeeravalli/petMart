package com.clayfin.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String toEmail;
	
	private String fromEmail;
	
	private String subject;
	
	@Lob
	private String body;
	
	private LocalDateTime timeStamp;
	
	
	
	
}
