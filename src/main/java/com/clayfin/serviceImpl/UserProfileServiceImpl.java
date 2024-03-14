package com.clayfin.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.common.util.RepoHelper;
import com.clayfin.enums.Status;
import com.clayfin.exception.UserException;
import com.clayfin.exception.UserLoginProfileException;
import com.clayfin.model.UserProfile;
import com.clayfin.repository.UserProfileRepository;
import com.clayfin.service.UserProfileService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	RepoHelper helper;
	
	@Autowired
	UserProfileRepository repository;
	
//	@Autowired
//	UserLoginProfileRepository loginProfileRepository;
	
	
	@Override
	public Status addUser(UserProfile profile, HttpServletRequest request)
			throws UserException, UserLoginProfileException {
		repository.save(profile);
		return Status.CREATED;
	}

	@Override
	public Status updateUser(UserProfile profile, HttpServletRequest request) throws UserException {
		UserProfile user = helper.getUserProfile(request);
		if(user.getId().equals(profile.getId())) {
			throw new UserException("Given User details is incorrect : "+profile.getUsername());
		}
		BeanUtils.copyProperties(profile, user, helper.getNullPropertyNames(profile));
		repository.save(user);
		return Status.UPDATED;
	}

	@Override
	public UserProfile getUserProfile(HttpServletRequest request) throws UserException {
		UserProfile user = helper.getUserProfile(request);
		return user;
	}

	@Override
	public Status deleteUser(HttpServletRequest request) throws UserException {
		UserProfile user = helper.getUserProfile(request);
		repository.deleteById(user.getId());
		return Status.DELETED;
	}

//	@Override
//	public Status addManager(UserLoginProfile loginProfile, HttpServletRequest request)
//			throws UserException, UserLoginProfileException {
//		
//		
//		
//		
//		return null;
//	}

}
