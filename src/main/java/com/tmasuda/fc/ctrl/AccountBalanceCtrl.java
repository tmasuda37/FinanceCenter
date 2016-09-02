package com.tmasuda.fc.ctrl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.model.key.AccountBalanceKey;
import com.tmasuda.fc.repo.AccountBalanceRepo;

@Controller
public class AccountBalanceCtrl extends AbstractCtrl<AccountBalance> {

	@Autowired
	private MonthlyCategoryBalanceCtrl categoryBalanceCtrl;

	@Autowired
	private AccountBalanceRepo accountBalanceRepo;

	@Override
	public AccountBalance getSavedModel(AccountBalance instantiated) {
		return accountBalanceRepo.findOne(instantiated.anAccountBalanceKey);
	}

	@Override
	public AccountBalance createNewModel(AccountBalance instantiated) {
		return accountBalanceRepo.save(instantiated);
	}

	@Override
	public void preRun(AccountBalance instantiated) {
	}

	@Override
	public void postRun(AccountBalance committed) {
	}

	public AccountBalance getBalance(Transaction aTransaction) {
		AccountBalance instantiated = getBalance(getBalanceKey(aTransaction));
		return accountBalanceRepo.findOne(instantiated.anAccountBalanceKey);
	}

	public void updateBalance(Transaction aTransaction) {
		AccountBalance instantiated = getBalance(getBalanceKey(aTransaction));
		instantiated.amount = calcBalance(instantiated, aTransaction);
		accountBalanceRepo.save(instantiated);

		categoryBalanceCtrl.updateBalance(aTransaction);
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

	private AccountBalance getBalance(AccountBalanceKey anAccountBalanceKey) {
		return getOrCreateModel(new AccountBalance(anAccountBalanceKey));
	}

	private AccountBalanceKey getBalanceKey(Transaction aTransaction) {
		return new AccountBalanceKey(aTransaction.account, aTransaction.currency, aTransaction.category.categoryApplyTo);
	}

}
