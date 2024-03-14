package com.clayfin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.request.dto.EmailDto;
import com.clayfin.serviceImpl.EmailSenderService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	EmailSenderService emailSenderService;
	
	
	@GetMapping("/sendMail")
	public String sendMail(@RequestBody EmailDto dto) {
		emailSenderService.sendEmail(dto);
		return "Successfully send mail to the customer: "+dto.getToEmail();
	}
	
	
	@PostMapping("/sendOtp")
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	public String sendOtp(@RequestBody EmailDto dto) {
		emailSenderService.sendOtp(dto);
		return "Successfully send mail to the customer: "+dto.getToEmail();
	}
	
	@PostMapping("/sendLoginMsg")
	public String sendLoginText(@RequestBody EmailDto mail) throws MessagingException {
		emailSenderService.sendLoginText(mail.getToEmail());
		return "Successfully send mail to the customer: "+mail.getToEmail();
	}
	
}
