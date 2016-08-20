package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.repo.TransactionRepo;

@Controller
public class TransactionCtrl {

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private BalanceCtrl balanceCtrl;

	public void createNewTransaction(Transaction aTransaction) {
		transactionRepo.save(aTransaction);
		balanceCtrl.updateBalance(aTransaction);
	}

}
