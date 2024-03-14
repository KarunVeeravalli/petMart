package com.clayfin.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.clayfin.model.UserProfile;
import com.clayfin.repository.UserProfileRepository;
import com.clayfin.security.util.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RepoHelper {
	
	@Autowired
	JwtUtils jwtService;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	public String getUsernameFromToken(HttpServletRequest request) {
		System.out.println(request.getLocalAddr());
		Enumeration<String> authHeader = request.getHeaders(HttpHeaders.AUTHORIZATION);
        String token = (authHeader.nextElement());
        if (token != null && token.startsWith("Bearer ")) {
        	token = token.substring(7);
      }
        String username = jwtService.getUserNameFromJwtToken(token);
        return username;
	}
	
	public String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}  
	
	
	public UserProfile getUserProfile(HttpServletRequest request) {
		System.out.println(getUsernameFromToken(request));
		UserProfile profile = userProfileRepository.findByEmail(getUsernameFromToken(request));
		System.out.println(profile.getEmail());
		return profile;
	}
	
	
	public String getOtp() {
		Integer upper = 999999;
		Integer lower = 111111;
		Integer otp = (int) (Math.random() * (upper - lower)) + lower;
		return otp.toString();
	}
	
	public String getOtpAndMsg(String otp) {
		
		System.out.println(otp);
		String body = "Dear Customer,\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+" "   + otp+" is your SECRET OTP for Accessing our Coin Currency at Browser on "+ LocalDate.now() +",  OTP valid for 5 mins.Pls do not share OTP with anyone.\n"
				+ "\n"
				+ "\n"
				+"\n"
				+ "In case you have any queries / clarifications, please call us at our Customer Service number :\n"
				+ "\n"
				+ "8340018900\n"
				+ "8340018900\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"+" Thank You For Using our Coin Currency :) ";
		
		return body;
	}
	
public String getLoginMsg(String mail)  {
		
		System.out.println(mail);
		
		
		String body = "Dear Customer,\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+" "   +"There's been a new sign-in to your Coin Currency account associated with the email address "
				+ "\n"+
				mail +",  on "+ LocalDateTime.now()+ ".\n"
				+ "\n"
				+ "If this wasn't you, you need to change your Coin Currency account password to protect your account."
				+ "\n"
				+ "\n"+
				    "https://change password"
			    
				+ "In case you have any queries / clarifications, please call us at our Customer Service number :\n"
				+ "\n"
				+ "8340018900\n"
				+ "8340018900\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"+" Thank You For Using our Coin Currency :) ";
		
		return body;
	}
	
	
	
	

}
