package com.tmasuda.fc.ctrl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Balance;
import com.tmasuda.fc.model.CategoryApplyTo;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.model.key.BalanceKey;
import com.tmasuda.fc.repo.BalanceRepo;

@Controller
public class BalanceCtrl {

	@Autowired
	private BalanceRepo balanceRepo;

	public void updateBalance(Transaction aTransaction) {
		Balance aBalance = getBalance(aTransaction);
		aBalance.amount = calcBalance(aBalance, aTransaction);
		balanceRepo.save(aBalance);
	}

	private BigDecimal calcBalance(Balance aBalance, Transaction aTransaction) {
		BigDecimal result;

		if (aTransaction.category.expense) {
			result = aBalance.amount.subtract(aTransaction.amount);
		} else {
			result = aBalance.amount.add(aTransaction.amount);
		}

		return result;
	}

	private Balance getBalance(Transaction aTransaction) {
		Balance savedBalance = balanceRepo.findOne(getBalanceKey(aTransaction));

		if (savedBalance != null) {
			return savedBalance;
		}

		return createNewBalance(aTransaction);
	}

	private Balance createNewBalance(Transaction aTransaction) {
		Balance aBalance = new Balance(getBalanceKey(aTransaction));
		return balanceRepo.save(aBalance);
	}

	private BalanceKey getBalanceKey(Transaction aTransaction) {
		CategoryApplyTo cateogoryApplyTo = aTransaction.category.cateogoryApplyTo;
		return new BalanceKey(aTransaction.account, cateogoryApplyTo, aTransaction.currency);
	}

}
