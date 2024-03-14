package com.clayfin.service;

import java.util.List;

import com.clayfin.enums.Status;
import com.clayfin.exception.TransactionException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Transaction;

import jakarta.servlet.http.HttpServletRequest;

public interface TransactionService {
	
	public Status addTransaction(Transaction transaction, HttpServletRequest request) throws UserException, TransactionException;
	
	public Status updateTransaction(Transaction transaction, Long id, HttpServletRequest request) throws UserException, TransactionException;
	
	public List<Transaction> getAllTransactions(HttpServletRequest request) throws UserException, TransactionException;
	
	public Transaction getTransaction(Long id, HttpServletRequest request) throws UserException, TransactionException;
	
	public Status deleteTransaction(Long id, HttpServletRequest request) throws UserException, TransactionException;

}
