package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllByUserProfileId(Long id);

}
