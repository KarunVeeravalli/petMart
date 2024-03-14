package com.clayfin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.exception.AddressException;
import com.clayfin.exception.OrderException;
import com.clayfin.exception.ProductException;
import com.clayfin.exception.TransactionException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Orders;
import com.clayfin.request.dto.OrderRequestDto;
import com.clayfin.response.dto.GeneralResponse;
import com.clayfin.service.OrdersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrdersService service;

	@PostMapping("/addOrder")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GeneralResponse> addOrder(@RequestBody OrderRequestDto dto, HttpServletRequest request)
			throws UserException, OrderException, ProductException, AddressException, TransactionException {
		var response = new GeneralResponse();
		response.setMessage("Order has been placed");
		response.setData(service.addOrder(dto, request));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getOrder/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GeneralResponse> getOrder(@PathVariable Long id, HttpServletRequest request)
			throws UserException, OrderException {
		var response = new GeneralResponse();
		response.setMessage("Order was found with id: "+id);
		response.setData(service.getOrder(id, request));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/updateOrder/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GeneralResponse> updateOrder(@PathVariable Long id,@RequestBody Orders order, HttpServletRequest request)
			throws UserException, OrderException {
		var response = new GeneralResponse();
		response.setMessage("Order has been updated for id : "+id);
		response.setData(service.updateOrder(id,order, request));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getAllOrders")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GeneralResponse> getAllOrders(HttpServletRequest request)
			throws UserException, OrderException {
		var response = new GeneralResponse();
		response.setMessage("All Orders found");
		response.setData(service.getAllOrders(request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<GeneralResponse> deleteOrder(@PathVariable Long id, HttpServletRequest request)
			throws UserException, OrderException {
		var response = new GeneralResponse();
		response.setMessage("Order has been deleted");
		response.setData(service.deleteOrder(id, request));
		return ResponseEntity.ok(response);
	}

}
