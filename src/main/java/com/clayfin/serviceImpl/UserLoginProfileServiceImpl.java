package com.clayfin.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clayfin.enums.ERole;
import com.clayfin.enums.Status;
import com.clayfin.exception.UserLoginProfileException;
import com.clayfin.model.Role;
import com.clayfin.model.UnauthUser;
import com.clayfin.model.UserLoginProfile;
import com.clayfin.model.UserProfile;
import com.clayfin.repository.RoleRepository;
import com.clayfin.repository.UserLoginProfileRepository;
import com.clayfin.repository.UserProfileRepository;
import com.clayfin.request.dto.EmailDto;
import com.clayfin.request.dto.LoginRequest;
import com.clayfin.request.dto.OtpDto;
import com.clayfin.request.dto.SignupRequest;
import com.clayfin.security.util.JwtUtils;
import com.clayfin.service.UserLoginProfileService;

@Service
public class UserLoginProfileServiceImpl implements UserLoginProfileService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserLoginProfileRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	OtpService otpService;
	
	@Autowired
	UnauathUserService unauathUserService;
	
	@Override
	public Status register(SignupRequest signUpRequest) throws UserLoginProfileException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UserLoginProfileException("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserLoginProfileException("Error: Email is already in use!");
		}
		EmailDto dto =  new EmailDto();
    	dto.setSubject("One Time Password for Accessing your Coin Currency");
    	dto.setToEmail(signUpRequest.getEmail());
		emailSenderService.sendOtp(dto);
		UnauthUser unauthUser = new UnauthUser();
		unauthUser.setEmail(signUpRequest.getEmail());
		unauthUser.setUsername(signUpRequest.getUsername());
		unauthUser.setPassword(signUpRequest.getPassword());
		unauathUserService.saveUser(unauthUser);
		return Status.OTP_SENT_SUCCESSFULLY;
	}

	@Override
	public Status verifyOtpForRegister(OtpDto dto) throws UserLoginProfileException {
		HttpStatus status = otpService.checkOtp(dto);
		
		UnauthUser signUpRequest = unauathUserService.getUserByEmail(dto.getEmail());
		// Create new user's account
		UserLoginProfile user = new UserLoginProfile(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		// creating new UserProfile
		UserProfile userProfile = new UserProfile();
		userProfile.setEmail(signUpRequest.getEmail());
		userProfile.setUsername(signUpRequest.getUsername());
		Set<Role> roles = new HashSet<>();
		/*
		 * if (strRoles.size()==0) { Role userRole =
		 * roleRepository.findByName(ERole.ROLE_USER); // .orElseThrow(() -> new
		 * RuntimeException("Error: Role is not found. user")); roles.add(userRole); }
		 * else { strRoles.forEach(role -> { switch (role) { case "admin": Role
		 * adminRole = roleRepository.findByName(ERole.ROLE_ADMIN); // .orElseThrow(()
		 * -> new RuntimeException("Error: Role is not found.")); roles.add(adminRole);
		 * 
		 * break; case "manager": Role modRole =
		 * roleRepository.findByName(ERole.ROLE_MANAGER); // .orElseThrow(() -> new
		 * RuntimeException("Error: Role is not found.")); roles.add(modRole);
		 * 
		 * break; default: Role userRole = roleRepository.findByName(ERole.ROLE_USER);
		 * // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		 * roles.add(userRole); } }); }
		 * 
		 */
		Role userRole = roleRepository.findByName(ERole.ROLE_USER);
		roles.add(userRole);
		user.setRoles(roles);
		userProfile.setRoles(roles);
		System.out.println(user.getRoles());
		if(status.equals(Status.ACCEPTED)){
			userRepository.save(user);
			userProfileRepository.save(userProfile);
		}
		return Status.CREATED;
	}

	@Override
	public String login(LoginRequest loginRequest) throws UserLoginProfileException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		return jwt;
	}

	@Override
	public List<String> getAllUserNames() throws UserLoginProfileException {
		List<UserLoginProfile> users = userRepository.findAll();
		List<String> names = new ArrayList<>();
		for (UserLoginProfile i : users) {
			names.add(i.getUsername());
		}
		return names;
	}

}
