package com.tmasuda.fc.ctrl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.repo.TransactionRepo;

@Controller
public class TransactionCtrl {

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private AccountBalanceCtrl accountBalanceCtrl;

	@Transactional
	public Transaction createTransaction(Transaction newTransaction) {
		Transaction savedTransaction = transactionRepo.save(newTransaction);

		accountBalanceCtrl.updateBalance(savedTransaction);

		return savedTransaction;
	}

}
