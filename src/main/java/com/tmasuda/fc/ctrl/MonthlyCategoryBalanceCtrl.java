package com.tmasuda.fc.ctrl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.MonthlyCategoryBalance;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.model.key.MonthlyCategoryBalanceKey;
import com.tmasuda.fc.repo.MonthlyCategoryBalanceRepo;

@Controller
public class MonthlyCategoryBalanceCtrl {

	@Autowired
	private MonthlyCategoryBalanceRepo monthlyCategoryBalanceRepo;

	@Transactional
	public void updateBalance(Transaction savedTransaction) {
		MonthlyCategoryBalance monthlyCategoryBalance = getBalance(getBalanceKey(savedTransaction));
		monthlyCategoryBalance.amount = calcBalance(monthlyCategoryBalance, savedTransaction);
		monthlyCategoryBalanceRepo.save(monthlyCategoryBalance);
	}

	protected BigDecimal calcBalance(MonthlyCategoryBalance aBalance, Transaction aTransaction) {
		BigDecimal result;

		if (aTransaction.category.toExpense) {
			result = aBalance.amount.subtract(aTransaction.amount);
		} else {
			result = aBalance.amount.add(aTransaction.amount);
		}

		return result;
	}

	private MonthlyCategoryBalance getBalance(MonthlyCategoryBalanceKey monthlyCategoryBalanceKey) {
		MonthlyCategoryBalance existing = monthlyCategoryBalanceRepo.findOne(monthlyCategoryBalanceKey);
		if (existing != null) {
			return existing;
		}

		MonthlyCategoryBalance newMonthlyCategoryBalance = new MonthlyCategoryBalance();
		newMonthlyCategoryBalance.aMonthlyCategoryBalanceKey = monthlyCategoryBalanceKey;
		return monthlyCategoryBalanceRepo.save(newMonthlyCategoryBalance);
	}

	private MonthlyCategoryBalanceKey getBalanceKey(Transaction aTransaction) {
		return new MonthlyCategoryBalanceKey(aTransaction.account, aTransaction.currency, aTransaction.category, aTransaction.calendar);
	}

}
