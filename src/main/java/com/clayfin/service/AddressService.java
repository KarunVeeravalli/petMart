package com.clayfin.service;

import java.util.List;

import com.clayfin.enums.Status;
import com.clayfin.exception.AddressException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Address;

import jakarta.servlet.http.HttpServletRequest;

public interface AddressService {
	
	public Status addAddress(Address address, HttpServletRequest request) throws AddressException, UserException;
	
	public List<Address> getAddress(HttpServletRequest request) throws AddressException, UserException;
	
	public Status updateAddress(Long id, Address address, HttpServletRequest request) throws AddressException, UserException;
	
	public Address getAddress(Long id, HttpServletRequest request) throws AddressException, UserException;
	
	public Status deleteAddress(Long id, HttpServletRequest request) throws AddressException, UserException;
}
