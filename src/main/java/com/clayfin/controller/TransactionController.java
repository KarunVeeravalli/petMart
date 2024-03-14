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

import com.clayfin.exception.TransactionException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Transaction;
import com.clayfin.response.dto.GeneralResponse;
import com.clayfin.service.TransactionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService service;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/addTransaction")
	public ResponseEntity<GeneralResponse> addTransaction(@RequestBody Transaction transaction, HttpServletRequest request)
			throws UserException, TransactionException {
		var response = new GeneralResponse();
		response.setMessage("Transaction has been added");
		response.setData(service.addTransaction(transaction, request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/updateTransaction/{id}")
	public ResponseEntity<GeneralResponse> updateTransaction(@RequestBody Transaction transaction,@PathVariable Long id,
			HttpServletRequest request) throws UserException, TransactionException {
		var response = new GeneralResponse();
		response.setMessage("Transaction has been updated with id : "+id);
		response.setData(service.updateTransaction(transaction, id, request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getAllTransactions")
	public ResponseEntity<GeneralResponse> getAllTransactions(HttpServletRequest request)
			throws UserException, TransactionException {
		var response = new GeneralResponse();
		response.setMessage("All Transaction found");
		response.setData(service.getAllTransactions(request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getTransaction/{id}")
	public ResponseEntity<GeneralResponse> getTransaction(@PathVariable Long id, HttpServletRequest request)
			throws UserException, TransactionException {
		var response = new GeneralResponse();
		response.setMessage("Transaction has been found with Id : "+id);
		response.setData(service.getTransaction(id, request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/deleteTransaction/{id}")
	public ResponseEntity<GeneralResponse> deleteTransaction(@PathVariable Long id, HttpServletRequest request)
			throws UserException, TransactionException {
		var response = new GeneralResponse();
		response.setMessage("Transaction has been deleted with id : "+id);
		response.setData(service.deleteTransaction(id, request));
		return ResponseEntity.ok(response);
	}

}
