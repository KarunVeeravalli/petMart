package com.clayfin.service;

import java.util.List;

import com.clayfin.enums.Status;
import com.clayfin.exception.AddressException;
import com.clayfin.exception.OrderException;
import com.clayfin.exception.ProductException;
import com.clayfin.exception.TransactionException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Orders;
import com.clayfin.request.dto.OrderRequestDto;

import jakarta.servlet.http.HttpServletRequest;

public interface OrdersService {
	
	public Status addOrder(OrderRequestDto dto, HttpServletRequest request) throws UserException, OrderException, ProductException, AddressException, TransactionException;
	
	public Orders getOrder(Long id, HttpServletRequest request) throws UserException, OrderException;
	
	public Status updateOrder(Long id, Orders order, HttpServletRequest request) throws UserException, OrderException;
	
	public List<Orders> getAllOrders(HttpServletRequest request) throws UserException, OrderException;
	
	public Status deleteOrder(Long id, HttpServletRequest request) throws UserException, OrderException;
	
	

}
