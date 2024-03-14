package com.clayfin.service;

import java.util.List;

import com.clayfin.enums.Status;
import com.clayfin.exception.UserLoginProfileException;
import com.clayfin.request.dto.LoginRequest;
import com.clayfin.request.dto.OtpDto;
import com.clayfin.request.dto.SignupRequest;

public interface UserLoginProfileService {
	
	public Status register(SignupRequest signupRequest) throws UserLoginProfileException;
	
	public Status verifyOtpForRegister(OtpDto dto) throws  UserLoginProfileException;
	
	public String login(LoginRequest loginRequest) throws UserLoginProfileException;
	
	public List<String> getAllUserNames() throws UserLoginProfileException;
	
}
