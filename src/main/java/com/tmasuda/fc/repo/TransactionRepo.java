package com.tmasuda.fc.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tmasuda.fc.model.Transaction;

public interface TransactionRepo extends PagingAndSortingRepository<Transaction, Long> {
}
