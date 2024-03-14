package com.clayfin.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.common.util.RepoHelper;
import com.clayfin.enums.Status;
import com.clayfin.exception.TransactionException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Transaction;
import com.clayfin.model.UserProfile;
import com.clayfin.repository.TransactionRepository;
import com.clayfin.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	RepoHelper helper;
	
	@Autowired
	TransactionRepository repository;
	
	@Override
	public Status addTransaction(Transaction transaction, HttpServletRequest request)
			throws UserException, TransactionException {
		UserProfile profile = helper.getUserProfile(request);
		transaction.setUserProfile(profile);
		repository.save(transaction);
		return Status.CREATED;
	}

	@Override
	public Status updateTransaction(Transaction transaction, Long id, HttpServletRequest request)
			throws UserException, TransactionException {
		UserProfile profile = helper.getUserProfile(request);
		Transaction olTransaction = repository.findById(id).get();
		if(olTransaction.getUserProfile().getId().equals(profile.getId())) {
			throw new UserException("Given Transacrion Id is incorrect : "+id);
		}
		BeanUtils.copyProperties(transaction, olTransaction, helper.getNullPropertyNames(transaction));
		repository.save(olTransaction);
		return Status.UPDATED;
	}

	@Override
	public List<Transaction> getAllTransactions(HttpServletRequest request) throws UserException, TransactionException {
		UserProfile profile = helper.getUserProfile(request);
		List<Transaction> transactions = repository.findAllByUserProfileId(profile.getId());
		if(transactions.equals(null)) {
			throw new TransactionException("No transaction found for the user : "+profile.getUsername());
		}
		return transactions;
	}

	@Override
	public Transaction getTransaction(Long id, HttpServletRequest request) throws UserException, TransactionException {
		UserProfile profile = helper.getUserProfile(request);
		Transaction olTransaction = repository.findById(id).get();
		if(olTransaction.getUserProfile().getId().equals(profile.getId())) {
			throw new UserException("Given Transacrion Id is incorrect : "+id);
		}
		return olTransaction;
	}

	@Override
	public Status deleteTransaction(Long id, HttpServletRequest request) throws UserException, TransactionException {
		UserProfile profile = helper.getUserProfile(request);
		Transaction olTransaction = repository.findById(id).get();
		if(olTransaction.getUserProfile().getId().equals(profile.getId())) {
			throw new UserException("Given Transacrion Id is incorrect : "+id);
		}
		repository.deleteById(id);
		return Status.DELETED;
	}

}
