package com.clayfin.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clayfin.model.UserLoginProfile;
import com.clayfin.repository.UserLoginProfileRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserLoginProfileRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserLoginProfile user = userRepository.findByEmail(email);
      if(user==null) {
    	  throw new UsernameNotFoundException("Email was not found with id: "+email);
      }

    return UserDetailsImpl.build(user);
  }

}
