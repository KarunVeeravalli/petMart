package com.clayfin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.request.dto.OtpDto;
import com.clayfin.serviceImpl.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {
	
	@Autowired
	OtpService service;
	
	@GetMapping("/getLastOtp/{email}")
	public String getLastOtp(@PathVariable String email) {
		return service.getLastOtp(email).getOtp();
	}
	
	@PutMapping("/checkOtp")
	public HttpStatus checkOtp(@RequestBody OtpDto otp) {		
		return service.checkOtp(otp);
		
	}
	
	
}
