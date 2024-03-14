package com.clayfin.service;

import java.util.List;

import com.clayfin.enums.Status;
import com.clayfin.exception.ProductException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Products;

import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
	
	public Status addProduct(Products products, HttpServletRequest request) throws UserException, ProductException;
	
	public Status addAllProducts(List<Products> products, HttpServletRequest requets) throws UserException, ProductException;
	
	public Status addAllProducts(String products, HttpServletRequest requets) throws UserException, ProductException;
	
	public Status updateProduct(Long id,Products products,  HttpServletRequest request) throws UserException, ProductException;
	
	public Products getProducts(Long id, HttpServletRequest request) throws UserException, ProductException;
	
	public List<Products> getAllProducts(HttpServletRequest request) throws UserException, ProductException;
	
	public Status deleteProduct(Long id, HttpServletRequest request) throws UserException, ProductException;
	
}
