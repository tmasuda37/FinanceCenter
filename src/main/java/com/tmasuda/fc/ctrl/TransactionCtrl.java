package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.repo.TransactionRepo;

@Controller
public class TransactionCtrl extends AbstractCtrl<Transaction> {

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private AccountBalanceCtrl accountBalanceCtrl;

	@Override
	public Transaction getSavedModel(Transaction instantiated) {
		return null;
	}

	@Override
	public Transaction createNewModel(Transaction instantiated) {
		return transactionRepo.save(instantiated);
	}

	@Override
	public void preRun(Transaction instantiated) {
	}

	@Override
	public void postRun(Transaction committed) {
		accountBalanceCtrl.updateBalance(committed);
	}

}
