package com.clayfin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.exception.UserException;
import com.clayfin.model.UserProfile;
import com.clayfin.response.dto.GeneralResponse;
import com.clayfin.service.UserProfileService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userProfile")
public class UserProfileController {

	@Autowired
	UserProfileService service;

//	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
//	@PostMapping("/addUser")
//	public ResponseEntity<GeneralResponse> addUser(@RequestBody UserProfile profile, HttpServletRequest request)
//			throws UserException, UserLoginProfileException {
//		var response = new GeneralResponse();
//		response.setMessage("Use the below Generated token to login");
//		response.setData(service.addUser(profile, request));
//		return ResponseEntity.ok(response);
//	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/updateUser")
	public ResponseEntity<GeneralResponse> updateUser(@RequestBody UserProfile profile, HttpServletRequest request)
			throws UserException {
		var response = new GeneralResponse();
		response.setMessage("User Profile has been Updated");
		response.setData(service.updateUser(profile, request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getUserProfile")
	public ResponseEntity<GeneralResponse> getUserProfile(HttpServletRequest request) throws UserException {
		var response = new GeneralResponse();
		response.setMessage("User Profile has been found");
		response.setData(service.getUserProfile( request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/deleteUser")
	public ResponseEntity<GeneralResponse> deleteUser(HttpServletRequest request) throws UserException {
		var response = new GeneralResponse();
		response.setMessage("User has been deleted from the DB");
		response.setData(service.deleteUser(request));
		return ResponseEntity.ok(response);
	}

}
