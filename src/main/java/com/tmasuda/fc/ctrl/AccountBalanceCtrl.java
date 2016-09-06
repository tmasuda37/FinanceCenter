package com.tmasuda.fc.ctrl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.model.key.AccountBalanceKey;
import com.tmasuda.fc.repo.AccountBalanceRepo;

@Controller
public class AccountBalanceCtrl {

	@Autowired
	private MonthlyCategoryBalanceCtrl categoryBalanceCtrl;

	@Autowired
	private AccountBalanceRepo accountBalanceRepo;

	public AccountBalance getBalance(Transaction aTransaction) {
		AccountBalance instantiated = getBalance(getBalanceKey(aTransaction));
		return accountBalanceRepo.findOne(instantiated.anAccountBalanceKey);
	}

	@Transactional
	public void updateBalance(Transaction savedTransaction) {
		AccountBalance updatingAccountBalance = getBalance(getBalanceKey(savedTransaction));
		updatingAccountBalance.amount = calcBalance(updatingAccountBalance, savedTransaction);
		accountBalanceRepo.save(updatingAccountBalance);

		categoryBalanceCtrl.updateBalance(savedTransaction);
	}

	protected BigDecimal calcBalance(AccountBalance aBalance, Transaction aTransaction) {
		BigDecimal result;

		if (aTransaction.category.toExpense) {
			result = aBalance.amount.subtract(aTransaction.amount);
		} else {
			result = aBalance.amount.add(aTransaction.amount);
		}

		return result;
	}

	private AccountBalance getBalance(AccountBalanceKey accountBalanceKey) {
		AccountBalance existing = accountBalanceRepo.findOne(accountBalanceKey);
		if (existing != null) {
			return existing;
		}

		AccountBalance newAccountBalance = new AccountBalance();
		newAccountBalance.anAccountBalanceKey = accountBalanceKey;
		return accountBalanceRepo.save(newAccountBalance);
	}

	private AccountBalanceKey getBalanceKey(Transaction aTransaction) {
		return new AccountBalanceKey(aTransaction.account, aTransaction.currency, aTransaction.category.categoryApplyTo);
	}

}
